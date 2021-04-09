package com.teletabist.clubby.user.services;

import com.teletabist.clubby.user.daos.ProfileDAO;
import com.teletabist.clubby.user.models.Profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class ProfileService {
       
    private final ProfileDAO profileDAO;

    @Autowired
    public ProfileService(@Qualifier("fakeDAO") ProfileDAO profileDAO){
        this.profileDAO = profileDAO;
    }

    public int addProfile(Profile profile){
        return profileDAO.insertProfile(0, profile);
    }
    

}