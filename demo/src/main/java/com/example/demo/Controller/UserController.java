package com.example.demo.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

		@GetMapping
		public String getUser(Model model) {
			model.addAttribute("something", "value of attribute something");
			return "people";
		}
}
