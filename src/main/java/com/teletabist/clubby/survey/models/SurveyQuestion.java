package com.teletabist.clubby.survey.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name = "survey_questions")
public class SurveyQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private Integer id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Column(length = 10)
    private Integer question_type;

    @Column(columnDefinition="TEXT")
    private String question_message;

    @Basic
    @JsonIgnore
    @Column(name = "answers", columnDefinition="TEXT")
    private String answers;

    @Transient
    @JsonProperty(value = "answers")
    private Collection<Answer> answersCollection;

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

    @PostLoad
    public void fillTransient() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Collection<Answer> answerFill = objectMapper.readValue(answers, new TypeReference<Collection<Answer>>() { });
            answersCollection = answerFill;
        } catch (JsonProcessingException e) {
            answersCollection = new ArrayList<Answer>();
        }
    }

    @PrePersist
    public void fillPersist() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            answers = objectMapper.writeValueAsString(answersCollection);
        } catch (JsonProcessingException e) {
            answers = "[]";
        }
    }

    public Collection<Answer> getAnswersCollection() {
        return answersCollection;
    }

    public void setAnswersCollection(Collection<Answer> answersCollection) {
        this.answersCollection = answersCollection;
        fillPersist();
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    
}
