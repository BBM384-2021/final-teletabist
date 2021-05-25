package com.teletabist.clubby.survey.models;

import java.util.Collection;

import com.teletabist.clubby.user.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSurveyRepository extends JpaRepository<UserSurvey, Integer>{
    public Collection<UserSurvey> findByUser(User user);
}
