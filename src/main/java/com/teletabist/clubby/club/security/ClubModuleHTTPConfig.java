package com.teletabist.clubby.club.security;

import com.teletabist.clubby.user.core.Roles;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@Order(20)
public class ClubModuleHTTPConfig {
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/club/**").hasRole(Roles.SYS_ADMIN.getName()).and().antMatcher("/club/**").formLogin();
    }
}
