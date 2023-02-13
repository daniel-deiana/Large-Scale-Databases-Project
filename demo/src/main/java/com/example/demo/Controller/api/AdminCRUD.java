package com.example.demo.Controller.api;

import com.example.demo.Service.AnimeService;
import com.example.demo.Service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@SessionAttributes("sessionVariables")
public class AdminCRUD {
    @Autowired
    AnimeService animeService;

    @Autowired
    UserService userService;

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
