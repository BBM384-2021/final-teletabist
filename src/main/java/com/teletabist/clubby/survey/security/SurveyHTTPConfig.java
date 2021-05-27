package com.teletabist.clubby.survey.security;

import com.teletabist.clubby.user.core.Roles;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(60)
public class SurveyHTTPConfig extends WebSecurityConfigurerAdapter{
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/surveys/**").authenticated().and().antMatcher("/surveys/**").formLogin();
    }
}
