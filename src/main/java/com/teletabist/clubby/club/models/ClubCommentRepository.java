package com.teletabist.clubby.club.models;

import org.springframework.data.jpa.repository.JpaRepository;

//@Repository <-- might be needed
public interface ClubCommentRepository extends JpaRepository<ClubComment, Integer> {
    ClubComment findByClub_idAndId(Integer club_id, Integer id);
    Iterable<ClubComment> findAllByClub_id(Integer club_id);
    ClubComment findByUser_id(Integer user_id);
}
