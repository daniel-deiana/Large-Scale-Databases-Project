package com.example.demo.Controller.api;

import com.example.demo.DTO.AnimeDTO;
import com.example.demo.DTO.FigureDTO;
import com.example.demo.Service.AnimeService;
import com.example.demo.Service.UserService;
import com.example.demo.Utilities.SVariables;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SessionAttributes("sessionVariables")
public class currentAlbum {
    @Autowired
    AnimeService animeService;

    @Autowired
    UserService userService;

    @RequestMapping("/api/currentAlbum")
    public @ResponseBody String returnAlbum(Model model) {
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

    @RequestMapping("/api/FindCharacter")
    public @ResponseBody String returnCharacter(Model model, @RequestParam(value = "name_fig") String name) {
        Gson gson = new Gson();
        SVariables sv = (SVariables) model.getAttribute("sessionVariables");
        FigureDTO fig =  userService.getCharacter(name, sv.myself);
        if(fig==null)
            return gson.toJson(null);
        return gson.toJson(fig);
    }

    @RequestMapping("/api/GetCharacters")
    public @ResponseBody String returnCharacter(Model model) {
        Gson gson = new Gson();
        SVariables sv = (SVariables) model.getAttribute("sessionVariables");
        List<FigureDTO> figures = userService.loadCharacters(sv.myself);
        return gson.toJson(figures);
    }

    @RequestMapping("/api/AddToTop10")
    public @ResponseBody String AddToTop10(Model model, @RequestParam(value = "name")String name_character){
        Gson gson = new Gson();
        SVariables sv = (SVariables) model.getAttribute("sessionVariables");
        return gson.toJson(userService.AddToTop10(sv.myself, name_character));
    }
    @RequestMapping("/api/removeFromTop10")
    public @ResponseBody String removeFromTop10(Model model, @RequestParam(value = "name")String name_character){
        Gson gson = new Gson();
        SVariables sv = (SVariables) model.getAttribute("sessionVariables");
        return gson.toJson(userService.removeFromTop10(sv.myself, name_character));
    }
}
