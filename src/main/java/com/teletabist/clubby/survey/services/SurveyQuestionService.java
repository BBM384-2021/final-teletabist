package com.teletabist.clubby.survey.services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import com.teletabist.clubby.survey.models.Survey;
import com.teletabist.clubby.survey.models.SurveyQuestion;
import com.teletabist.clubby.survey.models.SurveyQuestionRepository;
import com.teletabist.clubby.survey.models.SurveyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyQuestionService {
    private final SurveyRepository surveyRepository;
    private final SurveyQuestionRepository surveyQuestionRepository;

    @Autowired
    public SurveyQuestionService(SurveyRepository surveyRepository, SurveyQuestionRepository surveyQuestionRepository) {
        this.surveyRepository = surveyRepository;
        this.surveyQuestionRepository = surveyQuestionRepository;
    }

    public SurveyQuestion getQuestion(Integer id) {
        return surveyQuestionRepository.getOne(id);
    }

    public Collection<SurveyQuestion> getSurveyQuestions(Survey survey) {
        return surveyQuestionRepository.findAllBySurvey(survey);
    }

    public SurveyQuestion addQuestion(SurveyQuestion surveyQuestion, Survey survey) {
        
        if (survey.getQuestions().contains(surveyQuestion)) {
            return null;
        }

        surveyQuestion.setSurvey(survey);
        surveyQuestionRepository.save(surveyQuestion);
        SurveyQuestion currentQuestion = surveyQuestionRepository.getOne(surveyQuestion.getId());

        List<SurveyQuestion> questions = survey.getQuestions();
        questions.add(currentQuestion);
        survey.setQuestions(questions);

        
        surveyRepository.save(survey);

        return currentQuestion;
    }

    public SurveyQuestion updateQuestion(SurveyQuestion newQuestion, Integer id) {
        SurveyQuestion oldQuestion = surveyQuestionRepository.getOne(id);
        if (oldQuestion == null) {
            return null;
        }

        oldQuestion.setAnswersCollection(newQuestion.getAnswersCollection());
        oldQuestion.setQuestion_message(newQuestion.getQuestion_message());
        oldQuestion.setQuestion_type(newQuestion.getQuestion_type());
        oldQuestion.setWeight(newQuestion.getWeight());

        surveyQuestionRepository.save(oldQuestion);
        return oldQuestion;
    }

    @Transactional
    public Boolean deleteQuestion(Integer id, Survey survey) {
        SurveyQuestion surveyQuestion = surveyQuestionRepository.getOne(id);

        if (!survey.getQuestions().contains(surveyQuestion)) {
            return false;
        }
        survey.getQuestions().remove(surveyQuestion);
        surveyRepository.save(survey);
        if (surveyQuestionRepository.deleteBySurveyAndId(survey, id) == 0) {
            return false;
        }
        return true;
    }
}
