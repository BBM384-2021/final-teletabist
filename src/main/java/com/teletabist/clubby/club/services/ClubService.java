package com.teletabist.clubby.club.services;


import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.club.models.ClubRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class ClubService {
    private final ClubRepository clubRepository;

    @Autowired
    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    //TODO: add try/catch block and verification parts
    public Club addClub(Club club) {
        club = clubRepository.save(club);
        return club;
    }

    public Club getClub(String slug) {
        return clubRepository.findDistinctBySlug(slug);
    }

    public Iterable<Club> getAll() {
        return clubRepository.findAll();
    }


    public Club updateEntireClub(Club club) {
        Club updatingClub = clubRepository.findDistinctBySlug(club.getSlug());

        if (updatingClub == null) {
            try {
                throw new NotFoundException("There isn't a club with that slug");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        
        updatingClub.setName(club.getName());
        updatingClub.setDescription(club.getDescription());
        updatingClub.setProfile_photo_url(club.getProfile_photo_url());
        updatingClub.setWebsite(club.getWebsite());
        updatingClub.setLocation(club.getLocation());

        clubRepository.save(updatingClub);
        return updatingClub;
    }

    public Club deleteClub(String slug) {
        Club deletingClub = clubRepository.findDistinctBySlug(slug);

        if (deletingClub == null) {
            try {
                throw new NotFoundException("There isn't a club with that slug");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        
        clubRepository.delete(deletingClub);
        return deletingClub;
    }
    
    
}

