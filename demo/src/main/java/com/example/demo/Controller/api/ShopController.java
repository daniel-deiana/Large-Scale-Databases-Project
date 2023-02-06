package com.example.demo.Controller.api;

import com.example.demo.DTO.CharacterDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Model.Character;
import com.example.demo.Model.User;

import com.example.demo.Service.UserService;
import com.example.demo.Utilities.SVariables;
import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
/*
@RestController
@SessionAttributes("sessionVariables")
public class ShopController {

    @Autowired
    UserService userService;

    @PostMapping("/api/shop")
    public @ResponseBody String openPack(Model model) {
        Gson gson = new Gson();
        SVariables sv = (SVariables) model.getAttribute("sessionVariables");
        List<CharacterDTO> characterList = userService.openPack();
        String result = gson.toJson(characterList);
        return result;
    }


}
*/