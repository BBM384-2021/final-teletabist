package com.teletabist.clubby.admin.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(12)
public class AdminModuleSecurityConfig extends WebSecurityConfigurerAdapter {
    protected void configure(HttpSecurity http) throws Exception{
        http
        .authorizeRequests()
        .antMatchers("/admin/**")
        .authenticated()
        .and()
        .authorizeRequests()
        .antMatchers("/admin/**")
        .access("hasRole(\"SYS_ADMIN\")")
        .and()
        .antMatcher("/admin/**")
        .formLogin();
    }
    
}