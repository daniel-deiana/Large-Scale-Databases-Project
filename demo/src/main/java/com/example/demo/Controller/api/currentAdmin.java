package com.example.demo.Controller.api;

import com.example.demo.DTO.ResultSetDTO;
import com.example.demo.Service.AnimeService;
import com.example.demo.Service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
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
    public @ResponseBody String getTopReviewers(
            @RequestParam(value = "how_order") String how_order,
            @RequestParam(value = "year")String year
    ){
        Gson gson = new Gson();
        List<ResultSetDTO> AppreciatedAnime;
            if(!year.isEmpty()){
            int year_ = Integer.parseInt(year);
            AppreciatedAnime = animeService.getMostReviews(how_order, "user", year_);

        }else
        AppreciatedAnime = animeService.getMostReviews(how_order, "user");
            return gson.toJson(AppreciatedAnime.toArray());
    }

    @RequestMapping("/api/getCountryView")
    public @ResponseBody String getCountryView(@RequestParam(value = "how_order") String how_order) {
        Gson gson = new Gson();
        List<ResultSetDTO> list_user = userService.getCountryView(how_order);
        return gson.toJson(list_user.toArray());
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




    /************                          CRUD OPERATION                                    *************/




    @PostMapping("/api/AddAnime")
    public @ResponseBody String newAnime(Model model,
                                         @RequestParam(value = "title") String title,
                                         @RequestParam(value = "synopsis") String synopsis,
                                         @RequestParam(value = "episodes") int episodes,
                                         @RequestParam(value = "image") String image
    ) {

        Gson gson = new Gson();
        boolean result = animeService.addAnime(title,synopsis,episodes,image);
        if(!result)
            return gson.toJson("{\"type\":1, \"message\": \"Impossible\"}");
        return gson.toJson("{\"type\":0, \"message\": \"Possible\"}");
    }

    @PostMapping("/api/UpdateAnime")
    public @ResponseBody String updateAnime(Model model,
                                            @RequestParam(value = "title") String title,
                                            @RequestParam(value = "synopsis") String synopsis,
                                            @RequestParam(value = "episodes") int episodes,
                                            @RequestParam(value = "image") String image
    ) {
        Gson gson = new Gson();
        boolean result = animeService.updateAnime(title,synopsis,episodes,image);
        if(!result)
            return gson.toJson("{\"type\":1, \"message\": \"Impossible\"}");
        return gson.toJson("{\"type\":0, \"message\": \"Possible\"}");
    }

    @PostMapping("/api/AddCharacter")
    public @ResponseBody String newCharacter(Model model,
                                             @RequestParam(value = "name") String name,
                                             @RequestParam(value = "anime") String anime,
                                             @RequestParam(value = "image") String image
    ) {

        Gson gson = new Gson();
        boolean result = animeService.addCharacter(name,anime,image);
        if(!result)
            return gson.toJson("{\"type\":1, \"message\": \"Impossible\"}");
        return gson.toJson("{\"type\":0, \"message\": \"Possible\"}");
    }

    @PostMapping("/api/RemoveCharacter")
    public @ResponseBody String RemoveCharacter(Model model,
                                                @RequestParam(value = "name") String name,
                                                @RequestParam(value = "anime") String anime,
                                                @RequestParam(value = "image") String image
    ) {

        Gson gson = new Gson();
        boolean result = animeService.removeCharacter(name,anime,image);
        if(!result)
            return gson.toJson("{\"type\":1, \"message\": \"Impossible\"}");
        return gson.toJson("{\"type\":0, \"message\": \"Possible\"}");
    }

    @PostMapping("/api/deleteUser")
    public @ResponseBody String deleteUser(Model model,
                                           @RequestParam(value = "username") String user
    ) {

        Gson gson = new Gson();
        boolean result = userService.deleteUser(user);
        if(!result)
            return gson.toJson("{\"type\":1, \"message\": \"Impossible\"}");
        return gson.toJson("{\"type\":0, \"message\": \"Possible\"}");
    }


}
