package com.teletabist.clubby.club.models;

import com.teletabist.clubby.user.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubClubAdminBlacklistNodeRepository extends JpaRepository<SubClubAdminBlacklistNode, Integer>{
    public SubClubAdminBlacklistNode findByUser(User user);
}
