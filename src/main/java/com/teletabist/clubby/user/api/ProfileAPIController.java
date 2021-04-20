package com.teletabist.clubby.user.api;

import com.teletabist.clubby.user.models.Profile;
import com.teletabist.clubby.user.services.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/dev/profile")
public class ProfileAPIController {
    private final ProfileService profileService;
    

    @Autowired
    public ProfileAPIController(ProfileService profileService){   
        this.profileService = profileService;
    }

    @GetMapping("all")
    public Iterable<Profile> getProfile(){
        return this.profileService.getAll();
    }

    @PostMapping("create")
    public Profile createUser(@RequestBody Profile profile){
        return this.profileService.addProfile(profile);
    }

    @PostMapping("update")
    public Profile updateProfile(@RequestBody Profile profile) {
        return profileService.updateProfile(profile);
    }
    
    /*@GetMapping("get/{slug}")
    public Profile getProfile(@PathVariable String slug) {
        return profileService.getProfile(slug);
    }*/

}