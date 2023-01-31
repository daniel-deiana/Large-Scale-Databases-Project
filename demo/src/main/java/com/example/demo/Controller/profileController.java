package com.example.demo.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class profileController {
		@GetMapping("/profile")
		public String signup(){
						return "profile";
		}
}
