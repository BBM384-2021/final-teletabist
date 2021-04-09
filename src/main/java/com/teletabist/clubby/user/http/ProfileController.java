package com.teletabist.clubby.user.http;

import com.teletabist.clubby.user.models.Profile;
import com.teletabist.clubby.user.services.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService){
        this.profileService = profileService;
    }

    @PostMapping
    public void addProfile(Profile profile){
        profileService.addProfile(profile);
    }

    @GetMapping("user")
    @ResponseBody
    public String index(){
        return "Hello from profile controller";
    }
}