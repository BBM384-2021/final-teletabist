package com.teletabist.clubby.user.services;

import com.teletabist.clubby.user.models.Profile;
import com.teletabist.clubby.user.models.ProfileRepository;
import com.teletabist.clubby.user.models.User;
import com.teletabist.clubby.user.models.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This code is written by Bugra.
 * Because of merge issues, fixed by Yigit
 */
@Service
public class ProfileService {
       
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    public Profile addProfile(Profile profile){
        profile = profileRepository.save(profile);
        return profile;
    }

    /*public Profile getProfile(String slug) {
        return ;
    }*/

	public Iterable<Profile> getAll() {
		return profileRepository.findAll();
	}
    
    public Profile updateProfile(Profile profile) {
        profileRepository.save(profile);
        return profile;
    }

    public Profile updateProfile(String username, Profile profile){
        User u = userRepository.findByUsername(username);
        Profile _updated = null;
        if(u != null){
            Profile p = u.getProfile();
            if(profile.getBiography() != null) p.setBiography(profile.getBiography());
            p.setBirthday(profile.getBirthday());
            if(profile.getCurrent_location() != null) p.setCurrent_location(profile.getCurrent_location());
            if(profile.getGender() != null) p.setGender(profile.getGender());
            if(profile.getInstitution() != null) p.setInstitution(profile.getInstitution());
            if(profile.getProfile_photo_url() != null) p.setProfile_photo_url(profile.getProfile_photo_url());
            if(profile.getName() != null){
                if(!profile.getName().equals("")) p.setName(profile.getName());
                else p.setName(u.getUsername());
            } 
            _updated = this.profileRepository.save(p);
        }

        return _updated;
    }
/*
    public Profile deleteProfile(String slug) {
        //Profile deletingProfile = profileRepository.findDistinctBySlug(slug);
        profileRepository.delete(deletingProfile);
        return deletingProfile;
    }*/

}