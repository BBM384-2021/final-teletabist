package com.teletabist.clubby.user.security;

import com.teletabist.clubby.user.core.Roles;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(11)
public class ProfileHttpConfig extends WebSecurityConfigurerAdapter{
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/profile/**").hasRole(Roles.SYS_ADMIN.getName()).and().antMatcher("/profile/**").formLogin();
    }
}