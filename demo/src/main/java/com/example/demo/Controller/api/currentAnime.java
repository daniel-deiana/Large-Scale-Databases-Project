package com.example.demo.Controller.api;

import com.example.demo.Model.Review;
import com.example.demo.Service.AnimeService;
import com.example.demo.Service.UserService;
import com.example.demo.Utilities.SVariables;
import com.google.gson.Gson;
import com.example.demo.DTO.AnimeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@SessionAttributes("sessionVariables")
public class currentAnime {
    @Autowired
    AnimeService animeService;

    @Autowired
    UserService userService;

    @RequestMapping("/api/currentAnime")
    public @ResponseBody String returnAnime(Model model) {
        Gson gson = new Gson();
        SVariables sv = (SVariables) model.getAttribute("sessionVariables");
        if (sv == null || sv.animeToDisplay == null) {
            return gson.toJson("{\"type\":1, \"message\": \"Something went wrong\"}");
        }
        AnimeDTO anime =  animeService.getAnime(sv.animeToDisplay);
        if(anime==null)
            return gson.toJson("{\"type\":1, \"message\": \"Inexistent anime\"}");
        return gson.toJson(anime);
    }

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
    @RequestMapping("/api/getReviews")
    public @ResponseBody String getReviews(Model model) {
        Gson gson = new Gson();
        SVariables sv = (SVariables) model.getAttribute("sessionVariables");
        sv.currentPage = sv.currentPage + 1;
        Page<Review> review_list =  animeService.getReviews(sv.animeToDisplay, sv.currentPage);
        if(review_list.getContent().isEmpty())
            return gson.toJson("1");
        return gson.toJson(review_list);
    }

    @RequestMapping("/api/getPrecReviews")
    public @ResponseBody String getPrecReviews(Model model) {
        Gson gson = new Gson();
        SVariables sv = (SVariables) model.getAttribute("sessionVariables");
        if (sv.currentPage == 0)
            return gson.toJson("{\"type\":1, \"message\": \"Inexistent\"}");
        sv.currentPage = sv.currentPage - 2;
        return gson.toJson(sv.currentPage);
    }
}
