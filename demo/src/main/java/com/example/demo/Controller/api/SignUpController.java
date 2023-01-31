package com.example.demo.Controller.api;

import com.example.demo.Service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {
	@Autowired
	UserService userService;
	@GetMapping("/api/signup")
		public String signup(Model model){
			Gson gson = new Gson();
			return gson.toJson("{\"type\": 4, \"message\" : \"Something goes wrong\"}");
	}

}
