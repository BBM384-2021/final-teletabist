package com.teletabist.clubby.club.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClubRepository extends JpaRepository<Club, Integer>, JpaSpecificationExecutor<Club>{
    public Club findDistinctBySlug(String slug);
    public Integer deleteBySlug(String slug);
}
