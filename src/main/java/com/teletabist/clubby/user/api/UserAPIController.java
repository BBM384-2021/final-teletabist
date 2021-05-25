package com.teletabist.clubby.user.api;

import com.teletabist.clubby.club.services.ClubRoleService;
import com.teletabist.clubby.user.models.User;
import com.teletabist.clubby.user.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/dev/users")
public class UserAPIController {
    private final UserService userService;
    private final ClubRoleService clubRoleService;

    /**
     * User service bound with user service to provide the service
     * @param userService
     */ 
    @Autowired
    public UserAPIController(UserService userService, ClubRoleService clubRoleService){
        this.userService = userService;
        this.clubRoleService = clubRoleService;
    }

    @GetMapping
    public Iterable<User> index(){
        return this.userService.getAll();
    }

    @PostMapping
    public User store(@RequestBody User user){
        return this.userService.createUser(user);
    }

    @GetMapping("{username}")
    public User get(@PathVariable String username){
        return this.userService.getUser(username);
    }

    @PutMapping("{username}")
    @PatchMapping("{username}")
    public User update(@PathVariable String username, @RequestBody User user){
        return this.userService.updateUser(username, user);
    }

    @DeleteMapping("{username}")
    public boolean delete(@PathVariable String username){
        clubRoleService.deassignClubRole(userService.getUser(username));
        return this.userService.deleteUser(username);
    }

    @PostMapping("test/password")
    public boolean testPassword(@RequestBody User user){
        return this.userService.checkPassword(user);
    }
}