package com.teletabist.clubby.club.models;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscussionRepository extends JpaRepository<Discussion, Integer>{
    Discussion findByClub_idAndId(Integer club_id, Integer id);
    Iterable<Discussion> findAllByUser_id(Integer user_id);
    Iterable<Discussion> findAllByUser_id(Integer user_id, Sort sort);
    Discussion findByUser_id(Integer user_id);
}