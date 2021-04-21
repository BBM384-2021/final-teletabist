package com.teletabist.clubby.club.models;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRolesRepository extends JpaRepository<ClubRoles, Integer> {
    
    public Iterable<ClubRoles> findByClub_id(Integer club_id);
}
