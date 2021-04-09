package com.teletabist.clubby.user;

import com.teletabist.clubby.user.models.User;
import com.teletabist.clubby.user.models.UserRepository;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.UUID;

@Component
public class UserSeeder {
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Environment env;

    @Autowired
    public UserSeeder(
        UserRepository urep
    ){
        this.userRepository = urep;
    }

    @EventListener
    public void seed(ContextRefreshedEvent e) throws Exception{
        this.seedAdmin();
    }
    
    private void seedAdmin() throws Exception{
        User u = this.userRepository.findByUsername(env.getProperty("spring.security.user.name", "admin"));
        if(u == null){
            u = new User();
            u.setUsername(env.getProperty("spring.security.user.name", "admin"));
            u.setPassword(this.passwordEncoder.encode(env.getProperty("spring.security.user.password", "password")));
            u.setEmail_verification_token(UUID.randomUUID().toString());
            u.setEmail(env.getProperty("spring.security.user.email"));
            u.setVerified_at(new Timestamp(System.currentTimeMillis()));
            u = this.userRepository.save(u);
            if(u == null){
                throw new Exception("Default user cannot be created!");
            }else{
                LoggerFactory.getLogger(UserSeeder.class).info("\n\nAdmin usermane: "+u.getUsername()+"\n"+"Admin password: "+env.getProperty("spring.security.user.password", "password"));
            }
        }else{
            LoggerFactory.getLogger(UserSeeder.class).info("Admin user exists in database already");
        }

    }
}