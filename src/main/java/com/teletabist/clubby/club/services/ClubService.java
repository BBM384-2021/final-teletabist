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


    //TO-DO: add exception messages and things
    public Club updateClubName(String slug, String name) {
        Club updatingClub = clubRepository.findDistinctBySlug(slug);

        if (updatingClub == null) {
            try {
                throw new NotFoundException("There isn't a club with that slug");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        
        updatingClub.setName(name);
        clubRepository.save(updatingClub);
        return updatingClub;
    }

    //TO-DO: add exception messages and things
    public Club updateClubDesc(String slug, String description) {
        Club updatingClub = clubRepository.findDistinctBySlug(slug);

        if (updatingClub == null) {
            try {
                throw new NotFoundException("There isn't a club with that slug");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        
        updatingClub.setDescription(description);
        clubRepository.save(updatingClub);
        return updatingClub;
    }

    //TO-DO: add exception messages and things
    public Club updateClubPhotoURL(String slug, String photo_url) {
        Club updatingClub = clubRepository.findDistinctBySlug(slug);

        if (updatingClub == null) {
            try {
                throw new NotFoundException("There isn't a club with that slug");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        
        updatingClub.setProfile_photo_url(photo_url);
        clubRepository.save(updatingClub);
        return updatingClub;
    }

    //TO-DO: add exception messages and things
    public Club updateClubWebsite(String slug, String website) {
        Club updatingClub = clubRepository.findDistinctBySlug(slug);

        if (updatingClub == null) {
            try {
                throw new NotFoundException("There isn't a club with that slug");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        
        updatingClub.setWebsite(website);
        clubRepository.save(updatingClub);
        return updatingClub;
    }

    //TO-DO: add exception messages and things
    public Club updateClubLocation(String slug, String location) {
        Club updatingClub = clubRepository.findDistinctBySlug(slug);

        if (updatingClub == null) {
            try {
                throw new NotFoundException("There isn't a club with that slug");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        
        updatingClub.setLocation(location);
        clubRepository.save(updatingClub);
        return updatingClub;
    }
    
    
}

