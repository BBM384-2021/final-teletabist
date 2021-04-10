package com.teletabist.clubby.user.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@Order(10)
public class UserModuleHttpConfig extends WebSecurityConfigurerAdapter{
    protected void configure(HttpSecurity http) throws Exception{
       
        http.authorizeRequests().antMatchers("/user/**").authenticated().and().antMatcher("/user/**").formLogin();
    }
    
    @Bean
    public AuthenticationEntryPoint loginURLEntryPoint(){
        return new LoginUrlAuthenticationEntryPoint("/login");
    }

}