package com.teletabist.clubby.club.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@Order(21)
public class ClubCommentHttpConfig extends WebSecurityConfigurerAdapter {
    protected void configure(HttpSecurity http) throws Exception{

        http.authorizeRequests().antMatchers("/clubs/*/comments/**").authenticated().and().antMatcher("/clubs/*/comments/**").formLogin();
    }
}
