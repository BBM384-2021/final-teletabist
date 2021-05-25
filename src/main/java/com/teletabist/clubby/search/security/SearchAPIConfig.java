package com.teletabist.clubby.search.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@Order(55)
public class SearchAPIConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.antMatcher("/api/*/search/*")
            .csrf().disable()
            .sessionManagement().disable()
            .headers().frameOptions().disable()
            .and()
            .authorizeRequests()
            .antMatchers("/api/*/search/*")
            .permitAll()
            .and()
            .antMatcher("/api/*/search/*")
            .httpBasic()
            .authenticationEntryPoint(this.searchAEP());
    }

    @Bean
    public AuthenticationEntryPoint searchAEP(){
        BasicAuthenticationEntryPoint ep = new BasicAuthenticationEntryPoint();
        ep.setRealmName("search realm");
        return ep;
    }
}