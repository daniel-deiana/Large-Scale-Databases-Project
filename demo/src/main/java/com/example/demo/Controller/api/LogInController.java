package com.example.demo.Controller.api;

import com.example.demo.DOT.UserDTO;
import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import com.example.demo.Utilities.SVariables;
import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@RestController
@SessionAttributes("SVariables")
public class LogInController {
	@Autowired
	UserService userService;
	@PostMapping("/api/login")
		public String login(Model model,
							 @RequestParam(value = "username") String username,
						 	 @RequestParam(value = "password") String password
	) {

	Gson gson = new Gson();
	UserDTO user = userService.getUser(username);
	if(user==null)
		return gson.toJson("{\"type\":1, \"message\": \"Inexistent username\"}");
	String hashed = Hashing.sha256()
							.hashString(password, StandardCharsets.UTF_8)
							.toString();
	if(!hashed.equals(user.getPassword()))
		return gson.toJson("{\"type\":2, \"message\": \"Wrong password\"}");
	if((SVariables) model.getAttribute("SVariables") == null)
		model.addAttribute("SVariables", new SVariables());
	SVariables sv = (SVariables) model.getAttribute("SVariables");
	sv.myself = user.getUsername();
	sv.admin = userService.isAdmin(sv.myself);
	model.addAttribute("username",sv.myself);
	return gson.toJson("{\"type\": 0, \"message\" : \"ok\"}");
	}

}
