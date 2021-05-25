package com.teletabist.clubby.survey.models;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSurveyTakenRepository extends JpaRepository<UserSurveyTaken, Integer>{
    public Collection<UserSurveyTaken> findByUsersurvey(UserSurvey survey);
}
