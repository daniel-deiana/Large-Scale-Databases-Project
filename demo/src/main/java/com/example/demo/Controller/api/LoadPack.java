package com.example.demo.Controller.api;

import com.example.demo.DTO.AnimeDTO;
import com.example.demo.DTO.FigureDTO;
import com.example.demo.DTO.ReviewDTO;
import com.example.demo.Model.Anime;
import com.example.demo.Model.Review;
import com.example.demo.Service.UserService;
import com.example.demo.Utilities.SVariables;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@SessionAttributes("sessionVariables")
public class LoadPack {
    @Autowired
    UserService userService;

    @RequestMapping("/api/LoadPack")
    public @ResponseBody String returnCharacters(Model model) {
        SVariables sv = (SVariables) model.getAttribute("sessionVariables");
        Gson gson = new Gson();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        dtf.format(now);
        boolean available = userService.checkToken(sv.myself,now);
        if(!available)
            return gson.toJson(null);
        List<FigureDTO> pos_figures;
        pos_figures = userService.getReviewedFigures(sv.myself,3);
        userService.addHasCharacter(sv.myself, pos_figures);
        return gson.toJson(pos_figures);
    }

    @RequestMapping("/api/PossibleCards")
    public @ResponseBody String possibleCards(Model model) {
        SVariables sv = (SVariables) model.getAttribute("sessionVariables");
        Gson gson = new Gson();
        List<FigureDTO> pos_figures;

        pos_figures = userService.getReviewedFigures(sv.myself,10);
        return gson.toJson(pos_figures);
    }

}
