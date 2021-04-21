package com.teletabist.clubby.user.security;

import com.teletabist.clubby.user.core.Roles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

/**
 * This code is written by Bugra.
 * Because of merge issues, fixed by Yigit
 */
@Configuration
@Order(16)
public class ProfileAPIConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .antMatcher("/api/dev/profile/**")
            .csrf().disable()
            .sessionManagement().disable()
            .headers().frameOptions().disable()
        .and()
            .authorizeRequests()
            .antMatchers("/api/dev/profile/**")
            .hasRole(Roles.SYS_ADMIN.getName())
        .and()
            .antMatcher("/api/dev/profile/**")
            .httpBasic()
            .authenticationEntryPoint(this.ProfileeauthenticationEntryPoint());
    
    }

    @Bean
    public AuthenticationEntryPoint ProfileeauthenticationEntryPoint(){
        BasicAuthenticationEntryPoint ep = new BasicAuthenticationEntryPoint();
        ep.setRealmName("user module realm");
        return ep; 
    }
    
}