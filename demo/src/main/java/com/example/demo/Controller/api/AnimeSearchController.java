package com.example.demo.Controller.api;

import com.example.demo.DTO.AnimeDTO;
import com.example.demo.DTO.FigureDTO;
import com.example.demo.DTO.ResultSetDTO;
import com.example.demo.Model.Anime;
import com.example.demo.Model.Review;
import com.example.demo.Service.AnimeService;
import com.example.demo.Utilities.SVariables;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

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

	@RequestMapping("/api/GetSuggestedAnime")
	public @ResponseBody String returnGetSuggestedAnime(Model model) {
		Gson gson = new Gson();
		SVariables sv = (SVariables) model.getAttribute("sessionVariables");
		List<String> anime_to_review = animeService.GetSuggestedAnime(sv.myself);
		return gson.toJson(anime_to_review);
	}

	@RequestMapping("/api/GetAppreciatedAnime")
	public @ResponseBody String returnGetAppreciatedAnime(@RequestParam(value = "how_order") String how_order) {
		Gson gson = new Gson();
		List<ResultSetDTO> AppreciatedAnime = animeService.GetAppreciatedAnime(how_order);
		return gson.toJson(AppreciatedAnime.toArray());
	}

}
