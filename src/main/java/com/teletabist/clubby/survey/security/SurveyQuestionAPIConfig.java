package com.teletabist.clubby.survey.security;

import com.teletabist.clubby.user.core.Roles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@Order(40)
public class SurveyQuestionAPIConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .antMatcher("/api/dev/clubs/*/survey/questions/**")
            .csrf().disable()
            .sessionManagement().disable()
            .headers().frameOptions().disable()
        .and()
            .authorizeRequests()
            .antMatchers("/api/dev/clubs/*/survey/questions/**")
            .hasRole(Roles.SYS_ADMIN.getName())
        .and()
            .antMatcher("/api/dev/clubs/*/survey/questions/**")
            .httpBasic()
            .authenticationEntryPoint(this.surveyQuestionAuthenticationEntryPoint());
    }

    @Bean
    public AuthenticationEntryPoint surveyQuestionAuthenticationEntryPoint(){
        BasicAuthenticationEntryPoint ep = new BasicAuthenticationEntryPoint();
        ep.setRealmName("survey module realm");
        return ep; 
    }
}