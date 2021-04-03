package com.teletabist.clubby.user.api;

import java.util.List;

import com.teletabist.clubby.user.models.User;
import com.teletabist.clubby.user.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/dev/user")
public class UserAPIController {
    private final UserService userService;

    @Autowired
    public UserAPIController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("all")
    public List<User> createUser(){
        return this.userService.getAll();
    }

    @PostMapping("create")
    public User createUser(@RequestBody User user){
        user.updateId(-1);
        return this.userService.addPerson(user);
    }
}