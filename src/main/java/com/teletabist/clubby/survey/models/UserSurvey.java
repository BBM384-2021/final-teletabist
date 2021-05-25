package com.teletabist.clubby.survey.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teletabist.clubby.survey.core.SurveyState;
import com.teletabist.clubby.user.models.User;

@Entity
@Table(name = "user_surveys")
public class UserSurvey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private Integer id;

    @OneToMany(mappedBy = "usersurvey", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Collection<UserSurveyTaken> takens;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "usersid", nullable = false)
    @JsonIgnoreProperties({"roles", "profile", "created_at", "updated_at"})
    private User user;

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private SurveyState state;


    @Basic
    @JsonIgnore
    @Column(columnDefinition="TEXT", nullable = false)
    private String answers;

    @Transient
    @JsonProperty(value = "questions")
    private Collection<UserSurveyQuestion> userSurveys;
    

    @PostLoad
    public void fillTransient(){
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            Collection<UserSurveyQuestion> questionFill = objectMapper.readValue(answers, new TypeReference<Collection<UserSurveyQuestion>>(){});
            this.userSurveys = questionFill;
        }catch(JsonProcessingException e){
            userSurveys = new ArrayList<UserSurveyQuestion>();
        }
    }

    @PrePersist
    public void fillPersist(){
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            answers = objectMapper.writeValueAsString(this.userSurveys);
        }catch(JsonProcessingException e){
            this.answers = "[]";
        }

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Collection<UserSurveyTaken> getTakens() {
        return takens;
    }

    public void setTakens(Collection<UserSurveyTaken> user_surveys) {
        this.takens = user_surveys;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SurveyState getState() {
        return state;
    }

    public void setState(SurveyState state) {
        this.state = state;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answer) {
        this.answers = answer;
    }

    public Collection<UserSurveyQuestion> getUserSurveys() {
        return userSurveys;
    }

    public void setUserSurveys(Collection<UserSurveyQuestion> userSurveys) {
        this.userSurveys = userSurveys;
    }

    
}
