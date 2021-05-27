package com.teletabist.clubby.survey.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.validation.constraints.NotEmpty;

public class SurveyDTO {
    @NotEmpty
    ArrayList<QuestionDTO> questions;

    public SurveyDTO(){

    }

    public SurveyDTO(Survey s){
        this.questions = QuestionDTO.generateAll(s.getQuestions());
    }

    public ArrayList<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<QuestionDTO> questions) {
        this.questions = questions;
    }
}