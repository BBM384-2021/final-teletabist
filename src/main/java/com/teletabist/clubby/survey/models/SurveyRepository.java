package com.teletabist.clubby.survey.models;

import com.teletabist.clubby.club.models.Club;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SurveyRepository extends JpaRepository<Survey, Integer>, JpaSpecificationExecutor<Survey>{
    public Survey findByClub(Club club);
}
