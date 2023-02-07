package com.example.demo.Controller;
import com.example.demo.Utilities.SVariables;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class anime_searchController {
		@GetMapping("/anime_search")
		public String anime_search() {
			return "anime_search";
		}
}
