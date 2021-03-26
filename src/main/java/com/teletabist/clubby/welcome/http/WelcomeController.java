package com.teletabist.clubby.welcome.http;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping("/")
    String index(ModelMap map){
        map.put("welcome", "Welcome to Clubby!");
        return "welcome";
    }
}