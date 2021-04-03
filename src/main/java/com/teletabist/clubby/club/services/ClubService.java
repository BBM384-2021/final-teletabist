package com.teletabist.clubby.club.services;

import com.teletabist.clubby.club.daos.ClubDAO;
import com.teletabist.clubby.club.models.Club;

public class ClubService {
    private final ClubDAO clubDAO;

    public ClubService(ClubDAO clubDAO) {
        this.clubDAO = clubDAO;
    }

    public Club addClub(Club club) {
        return clubDAO.createClub(club);
    }
}
