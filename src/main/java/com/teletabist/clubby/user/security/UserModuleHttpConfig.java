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
        http
        .authorizeRequests()
        .antMatchers("/users/{username}", "/users")
        .authenticated()
        .and()
        .authorizeRequests()
        .antMatchers("/users/{username}/edit")
        .access("hasRole(\"SYS_ADMIN\") or hasRole(\"ADMIN\") or @profileSecurity.isUser(#username)")
        .and()
        .antMatcher("/users/**")
        .formLogin();
    }
    
    @Bean
    public AuthenticationEntryPoint loginURLEntryPoint(){
        return new LoginUrlAuthenticationEntryPoint("/login");
    }

}