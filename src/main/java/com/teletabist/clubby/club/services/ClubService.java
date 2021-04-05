package com.teletabist.clubby.club.services;


import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.club.models.ClubRepository;

public class ClubService {
    private final ClubRepository clubRepository;

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
}

