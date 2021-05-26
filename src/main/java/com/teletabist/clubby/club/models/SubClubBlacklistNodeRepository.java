package com.teletabist.clubby.club.models;

import com.teletabist.clubby.user.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubClubBlacklistNodeRepository extends JpaRepository<SubClubBlacklistNode, Integer>{
    SubClubBlacklistNode findByUserAndClub(User user, Club club);
}
