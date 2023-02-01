package com.example.demo.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class shopController {
		@GetMapping("/shop")
		public String shop(){
						return "shop";
		}
}
