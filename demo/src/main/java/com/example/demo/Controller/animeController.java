package com.example.demo.Controller;
import com.example.demo.Service.AnimeService;
import com.example.demo.Utilities.SVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import static java.lang.Math.ceilDiv;

@Controller
@SessionAttributes("sessionVariables")
public class animeController {
	@Autowired
	AnimeService animeService;
	@GetMapping("/anime")
	public String anime_search(Model model){
		if(model.getAttribute("sessionVariables") == null)
			model.addAttribute("sessionVariables", new SVariables());
		return "anime";
	}
	@GetMapping("/review")
	public String review(Model model){
		if(model.getAttribute("sessionVariables") == null)
			model.addAttribute("sessionVariables", new SVariables());
		SVariables sv = (SVariables) model.getAttribute("sessionVariables");
		sv.currentPage = -1;
		sv.lastPage = ceilDiv(animeService.getCountReviews(sv.animeToDisplay), 10);
		return "review";
	}
}
