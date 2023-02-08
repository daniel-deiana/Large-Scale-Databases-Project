package com.example.demo.Controller.api;

import com.example.demo.Model.Review;
import com.example.demo.Model.User;
import com.example.demo.Repository.Neo4j.Neo4jManager;
import com.example.demo.Service.UserService;
import com.example.demo.Utilities.SVariables;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.nio.charset.StandardCharsets;

@SessionAttributes("sessionVariables")
@RestController
public class MakeReviewController {

	private Neo4jManager manager = new Neo4jManager();
	@Autowired
	UserService userService;

		@PostMapping("/api/MakeReview")
		public String makeReview(Model model,
												 @RequestParam(value = "text_review") String text,
												 @RequestParam(value = "score_review") Integer score) {

		Gson gson = new Gson();
		SVariables sv = (SVariables) model.getAttribute("sessionVariables");
		String anime = sv.animeToDisplay;
		String username = sv.myself;
		Review review = new Review(username, anime, text, score);
		boolean check = userService.addReview(review);
		if (check)
			return gson.toJson("{\"type\": 0, \"message\" : \"ok\"}");
		else
			return gson.toJson("{\"type\": 1, \"message\" : \"Already exist\"}");
	}

}
