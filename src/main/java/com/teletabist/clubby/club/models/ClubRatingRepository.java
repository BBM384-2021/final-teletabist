package com.teletabist.clubby.club.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRatingRepository extends JpaRepository<ClubRating, Integer> {
    ClubRating findByClub_id(Integer club_id);
    Iterable<ClubRating> findAllByClub_id(Integer club_id);
}
