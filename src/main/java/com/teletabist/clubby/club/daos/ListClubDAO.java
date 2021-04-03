package com.teletabist.clubby.club.daos;

import java.util.List;

import com.teletabist.clubby.club.models.Club;

public class ListClubDAO implements ClubDAO {
    List<Club> clubs;

    @Override
    public Club createClub(Club club) {
        Club newClub = new Club(this.clubs.size(), club);
        this.clubs.add(newClub);
        return newClub;
    }

    @Override
    public Club getClub(int clubId) {
        if (clubId < this.clubs.size() || clubId >= 0) {
            return this.clubs.get(clubId);
        }
        return null;
    }

    @Override
    public Club updateClub(Club club) {
        // TODO Auto-generated method stub
        return null;
    }


    
}
