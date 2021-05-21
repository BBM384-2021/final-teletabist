package com.teletabist.clubby.survey.models;

import com.teletabist.clubby.club.models.Club;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Integer> {
    public Survey findByClub(Club club);
}
