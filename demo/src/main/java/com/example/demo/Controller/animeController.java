package com.example.demo.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class animeController {
		@GetMapping("/anime")
		public String anime_search(){
						return "anime";
		}
}
