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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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

    @OneToMany(mappedBy = "survey", cascade=CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<SurveyQuestion> questions;


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
    
}
