package com.teletabist.clubby.survey.models;

import java.util.List;

import javax.validation.constraints.NotEmpty;

public class SurveyDTO {
    @NotEmpty
    List<QuestionDTO> questions;

    public SurveyDTO(){

    }

    public SurveyDTO(Survey s){
        this.questions = QuestionDTO.generateAll(s.getQuestions());
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }
}