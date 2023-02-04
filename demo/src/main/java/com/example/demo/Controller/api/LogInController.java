package com.example.demo.Controller.api;

import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@RestController
public class LogInController {
	@Autowired
	UserService userService;
	@PostMapping("/api/login")
		public String login(Model model,
												 @RequestParam(value = "username") String username,
												 @RequestParam(value = "gender") String gender,
												 @RequestParam(value = "date") String date,
												 @RequestParam(value = "password") String password,
		                     @RequestParam(value = "country") String country) {

			String hashed = Hashing.sha256()
							.hashString(password, StandardCharsets.UTF_8)
							.toString();
			User user = new User(username,gender,date, country ,hashed);
			return "ok";
	}

}
