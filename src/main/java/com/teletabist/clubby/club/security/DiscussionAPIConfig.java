package com.teletabist.clubby.club.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@Order(27)
public class DiscussionAPIConfig extends WebSecurityConfigurerAdapter{
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/api/dev/clubs/*/discussions/**")
                .csrf().disable()
                .sessionManagement().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/api/dev/clubs/*/discussions/**")
                .permitAll()
                .and()
                .antMatcher("/api/dev/clubs/*/discussions/**")
                .httpBasic()
                .authenticationEntryPoint(this.discussionAuthenticationEntryPoint());
    }

    @Bean
    public AuthenticationEntryPoint discussionAuthenticationEntryPoint() {
        BasicAuthenticationEntryPoint ep = new BasicAuthenticationEntryPoint();
        ep.setRealmName("discussion realm");
        return ep;
    }
}