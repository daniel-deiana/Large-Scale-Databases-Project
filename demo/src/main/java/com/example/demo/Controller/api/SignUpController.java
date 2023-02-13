package com.example.demo.Controller.api;

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

@SessionAttributes("sessionVariables")
@RestController
public class SignUpController {

	@Autowired
	UserService userService;
		@PostMapping("/api/signup")
		public String signup(Model model,
												 @RequestParam(value = "username") String username,
												 @RequestParam(value = "gender") String gender,
												 @RequestParam(value = "date") String date,
												 @RequestParam(value = "country") String country,
												 @RequestParam(value = "password") String password) {

		Gson gson = new Gson();
		String hashed = Hashing.sha256()
							.hashString(password, StandardCharsets.UTF_8)
							.toString();
			User user = new User(username,gender,date, country ,hashed);
			userService.addUser(user);
		if(model.getAttribute("sessionVariables") == null)
			model.addAttribute("sessionVariables", new SVariables());

		SVariables sv = (SVariables) model.getAttribute("sessionVariables");
		sv.myself = user.getUsername();
		model.addAttribute("sessionVariables",sv);
		return gson.toJson("{\"type\": 0, \"message\" : \"ok\"}");


	}

}
