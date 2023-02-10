package com.example.demo.Controller.api;


import com.example.demo.DTO.UserDTO;
import com.example.demo.Service.UserService;
import com.example.demo.Utilities.SVariables;
import com.google.gson.Gson;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@SessionAttributes("sessionVariables")
public class SuggestedUser {
    @Autowired
    UserService userService;

    @RequestMapping("/api/suggestedUser")
    public @ResponseBody String SuggestUser(Model model) {
        SVariables sv = (SVariables) model.getAttribute("sessionVariables");
        return new Gson().toJson(userService.suggestedUsers(sv.myself));
    }
}