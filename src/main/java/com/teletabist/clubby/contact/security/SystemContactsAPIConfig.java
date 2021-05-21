package com.teletabist.clubby.contact.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@Order(35)
public class SystemContactsAPIConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.antMatcher("/api/dev/tickets/**")
            .csrf().disable()
            .sessionManagement().disable()
            .headers().frameOptions().disable()
            .and()
            .authorizeRequests()
            .antMatchers("/api/dev/tickets/**")
            .permitAll()
            .and()
            .antMatcher("/api/dev/tickets/**")
            .httpBasic()
            .authenticationEntryPoint(this.systemContactsAuthenticationEntryPoint());
    }

    @Bean
    public AuthenticationEntryPoint systemContactsAuthenticationEntryPoint(){
        BasicAuthenticationEntryPoint ep = new BasicAuthenticationEntryPoint();
        ep.setRealmName("system contacts realm");
        return ep;
    }
}