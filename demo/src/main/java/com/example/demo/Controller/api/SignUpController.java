package com.example.demo.Controller.api;

import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SignUpController {
	@Autowired
	UserService userService;
	@PostMapping("/api/signup")
		public String signup(Model model,@RequestParam(value = "username") String username, @RequestParam(value = "gender") String gender,@RequestParam(value = "date") String date){
			Gson gson = new Gson();
			User user = new User(username,gender,date);
			userService.addUser(user);
			return gson.toJson("{\"type\": \" " + gender + " \" , \"message\" :  \" " + date + " \" }");
	}

}
