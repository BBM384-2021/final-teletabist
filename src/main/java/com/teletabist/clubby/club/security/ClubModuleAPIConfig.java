package com.teletabist.clubby.club.security;

import com.teletabist.clubby.user.core.Roles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@Order(25)
public class ClubModuleAPIConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .antMatcher("/api/dev/clubs/**")
            .csrf().disable()
            .sessionManagement().disable()
            .headers().frameOptions().disable()
        .and()
            .authorizeRequests()
            .antMatchers("/api/dev/clubs/**")
            .hasRole(Roles.SYS_ADMIN.getName())
        .and()
            .authorizeRequests()
            .antMatchers("/api/dev/clubs/*/ban/**")
            .hasAnyRole(Roles.SYS_ADMIN.getName(), Roles.ADMIN.getName(), Roles.SUB_CLUB_ADMIN.getName())
        .and()
            .antMatcher("/api/dev/clubs/**")
            .httpBasic()
            .authenticationEntryPoint(this.clubAuthenticationEntryPoint());

    }

    @Bean
    public AuthenticationEntryPoint clubAuthenticationEntryPoint(){
        BasicAuthenticationEntryPoint ep = new BasicAuthenticationEntryPoint();
        ep.setRealmName("club module realm");
        return ep; 
    }
}