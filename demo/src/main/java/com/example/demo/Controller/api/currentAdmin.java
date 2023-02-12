package com.example.demo.Controller.api;

import com.example.demo.DTO.AnimeDTO;
import com.example.demo.DTO.ResultSetDTO;
import com.example.demo.Model.Anime;
import com.example.demo.Model.Review;
import com.example.demo.Service.AnimeService;
import com.example.demo.Service.UserService;
import com.example.demo.Utilities.SVariables;
import com.google.gson.Gson;
import org.neo4j.driver.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SessionAttributes("sessionVariables")
public class currentAdmin {
    @Autowired
    AnimeService animeService;

    @Autowired
    UserService userService;

    @RequestMapping("/api/getMostReviewedAnime")
    public @ResponseBody String getMostReviewedAnime(@RequestParam(value = "how_order") String how_order) {
        Gson gson = new Gson();
        List<ResultSetDTO> AppreciatedAnime = animeService.getMostReviews(how_order, "anime");
        return gson.toJson(AppreciatedAnime.toArray());
    }

    @RequestMapping("/api/getTopReviewedAnime")
    public @ResponseBody String getTopReviewedAnime(@RequestParam(value = "how_order") String how_order) {
        Gson gson = new Gson();
        List<ResultSetDTO> AppreciatedAnime = animeService.getTopReviewedAnime(how_order, 10);
        return gson.toJson(AppreciatedAnime.toArray());
    }

    @RequestMapping("/api/getTopReviewers")
    public @ResponseBody String getTopReviewers(@RequestParam(value = "how_order") String how_order) {
        Gson gson = new Gson();
        List<ResultSetDTO> AppreciatedAnime = animeService.getMostReviews(how_order, "user");
        return gson.toJson(AppreciatedAnime.toArray());
    }

    @RequestMapping("/api/getMostPopularUsers")
    public @ResponseBody String getMostPopularUsers(@RequestParam(value = "how_order") String how_order) {
        Gson gson = new Gson();
        List<ResultSetDTO> list_user = userService.getMostPopularUsers(how_order);
        return gson.toJson(list_user.toArray());
    }
}
