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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teletabist.clubby.user.models.User;

@Entity
@Table(name="user_surveys_taken")
public class UserSurveyTaken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private Integer id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "surveyid", nullable = false)
    @JsonIgnoreProperties({"questions"})
    private Survey survey;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "usersid", nullable = false)
    @JsonIgnoreProperties({"roles", "profile", "created_at", "updated_at"})
    private User user;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "usersurveysid",  nullable = false)
    @JsonBackReference
    private UserSurvey usersurvey;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserSurvey getUsersurvey() {
        return usersurvey;
    }

    public void setUsersurvey(UserSurvey user_survey) {
        this.usersurvey = user_survey;
    }

    
}
