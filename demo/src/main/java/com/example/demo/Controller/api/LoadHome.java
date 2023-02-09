package com.example.demo.Controller.api;

import com.example.demo.DTO.FigureDTO;
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
    public @ResponseBody String returnTop10(Model model) {
        Gson gson = new Gson();
        SVariables sv = (SVariables) model.getAttribute("sessionVariables");
        List<FigureDTO> figures = userService.loadTop10(sv.myself, null);
        return gson.toJson(figures);
    }



}

