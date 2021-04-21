package com.teletabist.clubby.user.http;


import com.teletabist.clubby.user.models.User;
import com.teletabist.clubby.user.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("users")
@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public String index(ModelMap map){
        map.put("users", this.userService.getAll());        
        return "user/index";
    }

    @GetMapping("{username}")
    public ModelAndView show(@PathVariable String username, ModelMap map){
        User u = this.userService.getUser(username);
        if(u == null)
            return new ModelAndView("404");
        
        map.put("user", u);        
        return new ModelAndView("user/single");
    }
}