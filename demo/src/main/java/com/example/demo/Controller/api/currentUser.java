package com.example.demo.Controller.api;

import com.example.demo.DOT.UserDTO;
import com.example.demo.Service.UserService;
import com.example.demo.Utilities.SVariables;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

@RestController
@SessionAttributes("sessionVariables")
public class currentUser {
    @Autowired
    UserService userService;
    @RequestMapping("/api/currentUser")
        public @ResponseBody String returnUserName(Model model){
        String username;
        Gson gson = new Gson();
        SVariables sv = (SVariables) model.getAttribute("sessionVariables");
        if(sv == null||sv.myself==null){
            return gson.toJson("{\"type\":1, \"message\": \"Something went wrong\"}");
        } else {
            username = sv.myself;
        }
        return gson.toJson("{\"type\":0, \"message\": \"ok\", \"username\":" + username+"}");}
}
