package com.teletabist.clubby.welcome.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeControllerAPI {
    @RequestMapping("api/v0/welcome")
    public String index(){
        return new String("{Welcome to api}");
    }
}