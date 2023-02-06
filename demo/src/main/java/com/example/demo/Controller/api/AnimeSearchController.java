package com.example.demo.Controller.api;

import com.example.demo.DOT.AnimeDTO;
import com.example.demo.Service.AnimeService;
import com.example.demo.Utilities.SVariables;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RestController
@SessionAttributes("sessionVariables")
public class AnimeSearchController {
	@Autowired
	AnimeService animeService;
	@PostMapping("/api/anime_search")
		public @ResponseBody String login(Model model,
										  @RequestParam(value = "title") String title
	) {

	Gson gson = new Gson();
	AnimeDTO anime = animeService.getAnime(title);
	if(anime==null)
		return gson.toJson("{\"type\":1, \"message\": \"Inexistent anime\"}");

	if(model.getAttribute("sessionVariables") == null)
		model.addAttribute("sessionVariables", new SVariables());
	SVariables sv = (SVariables) model.getAttribute("sessionVariables");
	sv.animeToDisplay = anime.getTitle();
	model.addAttribute("sessionVariables",sv);
	return gson.toJson("{\"type\": 0, \"message\" : \"ok\", \"anime\":\""+ sv.animeToDisplay+"\"}");
	}

}
