package com.teletabist.clubby.survey.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotBlank;

public class QuestionDTO {
    private Integer id;

    @NotBlank
    private Integer type;

    @NotBlank
    private String question;

    private Collection<AnswerDTO> answers;

    @NotBlank
    private Integer weight;

    private boolean delete = false;

    public static List<QuestionDTO> generateAll(List<SurveyQuestion> s){
        ArrayList<QuestionDTO> qs = new ArrayList<QuestionDTO>();
        for (SurveyQuestion q : s) {
            qs.add(new QuestionDTO(q));
        }
        return qs;
    }

    public QuestionDTO(){}

    public QuestionDTO(SurveyQuestion q){
        this.id = q.getId();
        this.type = q.getQuestion_type();
        this.question = q.getQuestion_message();
        this.answers = AnswerDTO.getAll(q.getAnswersCollection());
        this.weight = q.getWeight();
        this.delete = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer question_type) {
        this.type = question_type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question_message) {
        this.question = question_message;
    }

    public Collection<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(Collection<AnswerDTO> answersCollection) {
        this.answers = answersCollection;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}