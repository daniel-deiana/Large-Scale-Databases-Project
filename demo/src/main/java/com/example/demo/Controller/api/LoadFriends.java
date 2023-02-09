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
public class LoadFriends {
    @Autowired
    UserService userService;
    @RequestMapping("/api/FindUser")
    public @ResponseBody String FindUser(Model model,@RequestParam(value = "user") String name) {
        SVariables sv = (SVariables) model.getAttribute("sessionVariables");
        return new Gson().toJson(userService.loadProfile(name, sv.myself));
    }
    @RequestMapping("/api/followUser")
    public @ResponseBody boolean followUser(Model model, @RequestParam(value = "user")String username){
        SVariables sv = (SVariables) model.getAttribute("sessionVariables");
        return userService.followUser(sv.myself, username);
    }
    @RequestMapping("/api/unfollowUser")
    public @ResponseBody boolean unfollowUser(Model model, @RequestParam(value = "user")String username){
        SVariables sv = (SVariables) model.getAttribute("sessionVariables");
        return userService.unfollowUser(sv.myself, username);
    }

}
