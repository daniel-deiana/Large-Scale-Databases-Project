package com.example.demo.Controller.api;

import com.example.demo.DTO.AnimeDTO;
import com.example.demo.Service.AnimeService;
import com.example.demo.Service.UserService;
import com.example.demo.Utilities.SVariables;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@RestController
@SessionAttributes("sessionVariables")
public class TopAnime {
    @Autowired
    AnimeService animeService;

    @RequestMapping("/api/TopAnime")
    public @ResponseBody String returnTopAnime(Model model) {
        Gson gson = new Gson();
        SVariables sv = (SVariables) model.getAttribute("sessionVariables");
        List<AnimeDTO> top_anime;
        top_anime = animeService.getTopAnime();
        return gson.toJson("{\"type\":0, \"message\": \"ok\", \"username\":\"" + sv.myself + "\"}");
    }
}
