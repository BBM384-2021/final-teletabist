package com.teletabist.clubby.survey.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.teletabist.clubby.user.models.User;

@Entity
@Table(name = "user_surveys")
public class UserSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private Integer id;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name = "user_surveys_takenid")
    private List<UserSurveyTaken> user_surveys;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "usersid")
    private User user;

    @Column(length = 10)
    private Integer state;

    @Column(columnDefinition="TEXT")
    private String answer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<UserSurveyTaken> getUser_surveys() {
        return user_surveys;
    }

    public void setUser_surveys(List<UserSurveyTaken> user_surveys) {
        this.user_surveys = user_surveys;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    
}
