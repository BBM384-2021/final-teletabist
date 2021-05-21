package com.teletabist.clubby.survey.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "survey_questions")
public class SurveyQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private Integer id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Column(length = 10)
    private Integer question_type;

    @Column(columnDefinition="TEXT")
    private String question_message;

    @Column(columnDefinition="TEXT")
    private String answer;

    @Column(length = 10)
    private Integer weight;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Integer getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(Integer question_type) {
        this.question_type = question_type;
    }

    public String getQuestion_message() {
        return question_message;
    }

    public void setQuestion_message(String question_message) {
        this.question_message = question_message;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    
}
