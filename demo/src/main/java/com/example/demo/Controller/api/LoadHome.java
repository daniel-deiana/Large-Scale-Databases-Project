package com.example.demo.Controller.api;

import com.example.demo.DTO.FigureDTO;
import com.example.demo.Service.UserService;
import com.example.demo.Utilities.SVariables;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SessionAttributes("sessionVariables")
public class LoadHome {
    @Autowired
    UserService userService;

    @RequestMapping("/api/currentUser")
    public @ResponseBody String returnUserName(Model model) {
        Gson gson = new Gson();
        SVariables sv = (SVariables) model.getAttribute("sessionVariables");
        if (sv == null || sv.myself == null) {
            return gson.toJson("{\"type\":1, \"message\": \"Something went wrong\"}");
        }
        return gson.toJson("{\"type\":0, \"message\": \"ok\", \"username\":\"" + sv.myself + "\"}");
    }

    @RequestMapping("/api/Top10")
    public @ResponseBody String returnTop10(Model model, @RequestParam(value = "user") String name) {
        Gson gson = new Gson();
        SVariables sv = (SVariables) model.getAttribute("sessionVariables");
        List<FigureDTO> figures = userService.loadTop10(name, null);
        return gson.toJson(figures);
    }

    @RequestMapping("/api/PersonalTop10")
    public @ResponseBody String returnPersonalTop10(Model model) {
        Gson gson = new Gson();
        SVariables sv = (SVariables) model.getAttribute("sessionVariables");
        List<FigureDTO> figures = userService.loadTop10(sv.myself, null);
        return gson.toJson(figures);
    }



}

