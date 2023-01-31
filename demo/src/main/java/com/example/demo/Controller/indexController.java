package com.example.demo.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {
		@GetMapping("/index")
		public String signup(){
						return "index";
		}
}
