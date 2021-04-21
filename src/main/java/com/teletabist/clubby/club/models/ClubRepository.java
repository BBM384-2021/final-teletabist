package com.teletabist.clubby.club.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Integer>{
    public Club findDistinctBySlug(String slug);
    public Integer deleteBySlug(String slug);
}
