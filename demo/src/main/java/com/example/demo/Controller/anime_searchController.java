package com.example.demo.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class anime_searchController {
		@GetMapping("/anime_search")
		public String anime_search(){
						return "anime_search";
		}
}
