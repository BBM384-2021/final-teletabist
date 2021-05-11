package com.teletabist.clubby.club.models;


import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRoleRepository extends JpaRepository<ClubRole, Integer> {
    
    public Collection<ClubRole> findAllByClub_id(Integer club_id);
    
}
