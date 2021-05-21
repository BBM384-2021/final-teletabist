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

import com.teletabist.clubby.user.models.User;

@Entity
@Table(name="user_surveys_taken")
public class UserSurveyTaken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private Integer id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "surveyssurveyid")
    private Survey survey;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "usersid")
    private User user;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "usersurveysid")
    private UserSurvey user_survey;

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

    public UserSurvey getUser_survey() {
        return user_survey;
    }

    public void setUser_survey(UserSurvey user_survey) {
        this.user_survey = user_survey;
    }

    
}
