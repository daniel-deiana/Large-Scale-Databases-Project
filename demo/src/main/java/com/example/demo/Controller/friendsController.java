package com.example.demo.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class friendsController {
    @GetMapping("/friends")
    public String Friends(){
        return "friends";
    }
}
