package com.teletabist.clubby.survey.services;

import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.survey.models.Survey;
import com.teletabist.clubby.survey.models.SurveyQuestion;
import com.teletabist.clubby.survey.models.SurveyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyService {
    private final SurveyRepository surveyRepository;

    @Autowired
    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    public Iterable<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    public Survey getSurvey(Club club) {
        
        return surveyRepository.findByClub(club);
    }

    public Survey createSurvey(Club club, Survey survey) {

        Survey clubSurvey = surveyRepository.findByClub(club);
        if (clubSurvey != null) {
            return null;
        }

        survey.setClub(club);
        for (SurveyQuestion question : survey.getQuestions()) {
            question.setSurvey(survey);
        }
        survey = surveyRepository.save(survey);
        return survey;
    }
}
