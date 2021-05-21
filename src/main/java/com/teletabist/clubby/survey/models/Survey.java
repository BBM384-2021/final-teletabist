package com.teletabist.clubby.survey.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.teletabist.clubby.club.models.Club;

@Entity
@Table(name = "surveys")
public class Survey {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private Integer survey_id;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "club_id", referencedColumnName = "id")
    private Club club;

    @OneToMany(mappedBy = "survey", fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
    private List<SurveyQuestion> questions;

    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
    private List<UserSurveyTaken> user_survey_taken;

    public Integer getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(Integer survey_id) {
        this.survey_id = survey_id;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public List<SurveyQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<SurveyQuestion> questions) {
        this.questions = questions;
    }

    public List<UserSurveyTaken> getUser_survey_taken() {
        return user_survey_taken;
    }

    public void setUser_survey_taken(List<UserSurveyTaken> user_survey_taken) {
        this.user_survey_taken = user_survey_taken;
    }

    
}
