package com.example.demo.Controller.api;

import com.example.demo.DOT.UserDTO;
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

@RestController
@SessionAttributes("sessionVariables")
public class ShopController {

    @Autowired
    CharacterService characterService;
}
