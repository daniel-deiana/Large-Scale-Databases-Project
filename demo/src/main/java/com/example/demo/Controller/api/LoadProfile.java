package com.example.demo.Controller.api;


import com.example.demo.Service.UserService;
import com.example.demo.Utilities.SVariables;
import com.google.gson.Gson;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@SessionAttributes("sessionVariables")
public class LoadProfile {
    @Autowired
    UserService userService;
    @GetMapping("api/LoadMe")
    public @ResponseBody String Me(Model model) {
        SVariables sv = (SVariables) model.getAttribute("sessionVariables");
        if (sv == null)
            return new Gson().toJson(false);
        if (sv.myself == null)
            return new Gson().toJson(false);
        return new Gson().toJson(userService.loadProfile(sv.myself, null));
    }

}
