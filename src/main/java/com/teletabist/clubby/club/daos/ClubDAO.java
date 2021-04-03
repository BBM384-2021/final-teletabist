package com.teletabist.clubby.club.daos;

import com.teletabist.clubby.club.models.Club;

public interface ClubDAO {
    Club createClub(Club club);
    Club getClub(int clubId);
    Club updateClub(Club club);
}
