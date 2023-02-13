package com.example.demo.Controller.api;

import com.example.demo.DTO.AnimeDTO;
import com.example.demo.DTO.ResultSetDTO;
import com.example.demo.Model.Anime;
import com.example.demo.Model.Review;
import com.example.demo.Service.AnimeService;
import com.example.demo.Service.UserService;
import com.example.demo.Utilities.SVariables;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.neo4j.driver.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@SessionAttributes("sessionVariables")
public class currentAdmin {
    @Autowired
    AnimeService animeService;

    @Autowired
    UserService userService;

    @SneakyThrows
    @RequestMapping("/api/getMostReviewedAnime")
    public @ResponseBody String getMostReviewedAnime(
                                                    @RequestParam(value = "how_order") String how_order,
                                                    @RequestParam(value = "year")String year
    ) {
        Gson gson = new Gson();
        List<ResultSetDTO> AppreciatedAnime;
        if(!year.isEmpty()){
            int year_ = Integer.parseInt(year);
            AppreciatedAnime = animeService.getMostReviews(how_order, "anime", year_);

        }else
            AppreciatedAnime = animeService.getMostReviews(how_order, "anime");
        return gson.toJson(AppreciatedAnime.toArray());
    }

    @RequestMapping("/api/getTopReviewedAnime")
    public @ResponseBody String getTopReviewedAnime(
                                @RequestParam(value = "how_order") String how_order,
                                @RequestParam(value = "year")String year
    ) {
        Gson gson = new Gson();
        List<ResultSetDTO> AppreciatedAnime;
        if(!year.isEmpty()){
            int year_ = Integer.parseInt(year);
            AppreciatedAnime = animeService.getTopReviewedAnime(how_order, 10, year_);

        }else
            AppreciatedAnime = animeService.getTopReviewedAnime(how_order, 10);
        return gson.toJson(AppreciatedAnime.toArray());
    }

    @RequestMapping("/api/getTopReviewers")
    public @ResponseBody String getTopReviewers(@RequestParam(value = "how_order") String how_order) {
        Gson gson = new Gson();
        //List<ResultSetDTO> AppreciatedAnime = animeService.getMostReviews(how_order, "user","2016","5");
        //return gson.toJson(AppreciatedAnime.toArray());
        return null;
    }

    @RequestMapping("/api/getMostPopularUsers")
    public @ResponseBody String getMostPopularUsers(@RequestParam(value = "how_order") String how_order) {
        Gson gson = new Gson();
        List<ResultSetDTO> list_user = userService.getMostPopularUsers(how_order);
        return gson.toJson(list_user.toArray());
    }

    @RequestMapping("/api/getMostLovedCharacter")
    public @ResponseBody String getMostLovedCharacter(@RequestParam(value = "how_order") String how_order) {
        Gson gson = new Gson();
        List<ResultSetDTO> list_character = animeService.getMostLovedCharacter(how_order);
        return gson.toJson(list_character.toArray());
    }

    @RequestMapping("/api/getMostRareCharacter")
    public @ResponseBody String getMostRaredCharacter(@RequestParam(value = "how_order") String how_order) {
        Gson gson = new Gson();
        List<ResultSetDTO> list_character = animeService.getMostRareCharacter(how_order);
        return gson.toJson(list_character.toArray());
    }

    @RequestMapping("/api/getMostUnusedCharacter")
    public @ResponseBody String getMostUnusedCharacter(@RequestParam(value = "how_order") String how_order) {
        Gson gson = new Gson();
        List<ResultSetDTO> list_character = animeService.getMostUnusedCharacter(how_order);
        return gson.toJson(list_character.toArray());
    }
}
