package com.example.demo.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class settingController {

		@GetMapping("/home")
		String settingPage (Model model) {
				return "home";
		}
}
