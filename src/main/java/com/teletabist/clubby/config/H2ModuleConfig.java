package com.teletabist.clubby.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1)
public class H2ModuleConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    Environment env;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if(env.getProperty("deployment", "production").equals("local") || env.getProperty("deployment", "production").equals("development")){
            http.antMatcher("/h2-console/**").headers().frameOptions().disable(); //disable frame-options for non-ssl tests & disabling CSRF security
            http.authorizeRequests().antMatchers("/h2-console/**").authenticated().and().antMatcher("/h2-console/**").formLogin();
            http.antMatcher("/h2-console/**").csrf().disable();
        }

        
    }
}