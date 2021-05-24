package com.teletabist.clubby.survey.models;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestion, Integer>{
    public Collection<SurveyQuestion> findAllBySurvey(Survey survey);
    public Integer deleteBySurveyAndId(Survey survey, Integer id);
}
