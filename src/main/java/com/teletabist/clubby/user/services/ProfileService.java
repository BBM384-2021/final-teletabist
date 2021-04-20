package com.teletabist.clubby.user.services;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.teletabist.clubby.user.models.Profile;
import com.teletabist.clubby.user.models.ProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProfileService {
       
    private ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile addProfile(Profile profile){
        profile = profileRepository.save(profile);
        return profile;
    }

    /*public Profile getProfile(String slug) {
        return ;
    }*/

	public Iterable<Profile> getAll() {
		return null;
	}
    
    public Profile updateProfile(Profile profile) {
        profileRepository.save(profile);
        return profile;
    }
/*
    public Profile deleteProfile(String slug) {
        //Profile deletingProfile = profileRepository.findDistinctBySlug(slug);
        profileRepository.delete(deletingProfile);
        return deletingProfile;
    }*/

}