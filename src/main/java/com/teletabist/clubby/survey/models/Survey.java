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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teletabist.clubby.club.models.Club;

@Entity
@Table(name = "surveys")
public class Survey {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10, name="survey_id")
    private Integer id;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "club_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"subclubs", "description"})
    private Club club;

    @OneToMany(mappedBy = "survey", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<SurveyQuestion> questions;


    public Integer getId() {
        return id;
    }

    public void setId(Integer survey_id) {
        this.id = survey_id;
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
