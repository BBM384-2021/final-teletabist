package com.teletabist.clubby.user.http;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProfileController {
    public ProfileController(){

    }

    @GetMapping("user")
    @ResponseBody
    public String index(){
        return "Hello from user controller";
    }
}