package com.teletabist.clubby.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    Environment env;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.csrf().disable().authorizeRequests().antMatchers("/").permitAll();
        if(env.getProperty("deployment", "production").equals("local") || env.getProperty("deployment", "production").equals("development")){
            http.headers().frameOptions().disable(); //disable frame-options for non-ssl tests & disabling CSRF security
        }

        http
            .csrf()
                .ignoringAntMatchers("/api/**", "/h2-console/**")
        .and()
            .formLogin()
        .and()
            .authorizeRequests()
            .antMatchers("/","/api/**","/public/js/*", "/public/css/*","/public/img/*", "/h2-console/**")
            .permitAll()
            .anyRequest()
            .authenticated();
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}