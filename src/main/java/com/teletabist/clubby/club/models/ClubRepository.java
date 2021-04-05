package com.teletabist.clubby.club.models;

import org.springframework.data.repository.CrudRepository;

public interface ClubRepository extends CrudRepository<Club, Integer>{
    public Club findDistinctBySlug(String slug);
}
