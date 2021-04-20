package com.teletabist.clubby.user.http;

import com.teletabist.clubby.user.models.Profile;
import com.teletabist.clubby.user.services.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("profile")
    @ResponseBody
    public String index(ModelMap map){
        map.put("profiles", profileService.getAll());
        return "profile/index";
    }

    @GetMapping("get/{slug}")
    public String getProfile(@PathVariable String slug, ModelMap map) {
        //map.put("profile", profileService.getProfile(slug));
        return "profile";
    }
}