package com.teletabist.clubby.user.api;

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

    /**
     * User service bound with user service to provide the service
     * @param userService
     */ 
    @Autowired
    public UserAPIController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("all")
    public Iterable<User> createUser(){
        return this.userService.getAll();
    }

    @PostMapping("create")
    public User createUser(@RequestBody User user){
        return this.userService.addPerson(user);
    }

    @PostMapping("test/password")
    public boolean testPassword(@RequestBody User user){
        return this.userService.checkPassword(user);
    }
}