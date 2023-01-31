package com.example.demo.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class anime_searchController {
		@GetMapping("/anime_search")
		public String signup(){
						return "anime_search";
		}
}
