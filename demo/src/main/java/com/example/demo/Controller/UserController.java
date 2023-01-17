package com.example.demo.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
		@GetMapping
		public String userPage(Model model) {
			model.addAttribute("something", "value of attribute something");
			return "user";
		}
}
