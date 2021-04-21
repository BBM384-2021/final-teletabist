package com.teletabist.clubby.club.services;


import java.security.InvalidParameterException;
import java.util.Random;

import javax.transaction.Transactional;

import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.club.models.ClubRepository;
import com.teletabist.clubby.club.models.ClubRolesRepository;
import com.teletabist.clubby.user.models.UserRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class ClubService {
    private final ClubRepository clubRepository;
    //private final ClubRolesRepository clubRolesRepository;
    //private final UserRoleRepository userRoleRepository;

    @Autowired
    public ClubService(ClubRepository clubRepository, ClubRolesRepository clubRolesRepository, UserRoleRepository userRoleRepository) {
        this.clubRepository = clubRepository;
        //this.clubRolesRepository = clubRolesRepository;
        //this.userRoleRepository = userRoleRepository;
    }

    //TODO: add try/catch block and verification parts
    public Club addClub(Club club) {
        
        if (club.getSlug() == null) {

            club.setSlug(createSlug(club));

        } else {
            Club checkClub = clubRepository.findDistinctBySlug(club.getSlug());
            
            if (checkClub != null) {
                throw new InvalidParameterException("This slug already exists");
            }
        }
        club = clubRepository.save(club);
        return club;
    }

    private String createSlug(Club club) {

        String slugInMaking = club.getName().toLowerCase().replaceAll(" ", "-");

        if (slugInMaking.length() > 144) {
            slugInMaking = slugInMaking.substring(0, 135);
        }

        Club checkingClub = clubRepository.findDistinctBySlug(slugInMaking);
        String slugInChange = slugInMaking;

        for (int i = 0; i < 10 && checkingClub != null; i++) {
            Random random = new Random();
            slugInChange = slugInMaking + random.nextInt(1000000000);
            checkingClub = clubRepository.findDistinctBySlug(slugInChange);
            System.out.println(i);
        }

        return slugInChange;
    }

    public Club getClub(String slug) {
        return clubRepository.findDistinctBySlug(slug);
    }

    public Iterable<Club> getAll() {
        return clubRepository.findAll();
    }



    public Club updateEntireClub(Club club, String slug) {
        Club updatedClub = clubRepository.findDistinctBySlug(slug);

        if (updatedClub == null) {
            try {
                throw new NotFoundException("There isn't a club with that slug");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        
        if (club.getSlug() == null) {
            updatedClub.setSlug(updatedClub.getSlug());
        } else {
            Club updatingClub = clubRepository.findDistinctBySlug(club.getSlug());

            if (updatingClub == null) {
                updatedClub.setSlug(club.getSlug());
            } else if (updatingClub.getId() != updatedClub.getId()) {
                throw new InvalidParameterException("Cannot be updated to this slug. This slug already exists");
            } else {
                updatedClub.setSlug(updatedClub.getSlug());
            }

        }

        updatedClub.setName((club.getName() != null)?club.getName():updatedClub.getName());
        updatedClub.setDescription((club.getDescription() != null)?club.getDescription():updatedClub.getDescription());
        updatedClub.setProfile_photo_url((club.getProfile_photo_url() != null)?club.getProfile_photo_url():updatedClub.getProfile_photo_url());
        updatedClub.setWebsite((club.getWebsite() != null)?club.getWebsite():updatedClub.getWebsite());
        updatedClub.setLocation((club.getLocation() != null)?club.getLocation():updatedClub.getLocation());

        clubRepository.save(updatedClub);
        return updatedClub;
    }

    @Transactional
    public Integer deleteClub(String slug) {
        return clubRepository.deleteBySlug(slug);
    }
    
    /*public Iterable<UserRole> getMembers(Integer club_id) {
        Iterable<ClubRoles> cr;
        cr = clubRolesRepository.findByClub_id(club_id);
        for (ClubRoles clubRoles : cr) {
            userRoleRepository.findById(clubRoles)
        }
        return ;
    }*/
}

