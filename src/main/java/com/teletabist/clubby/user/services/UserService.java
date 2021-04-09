package com.teletabist.clubby.user.services;

import java.util.UUID;

import com.teletabist.clubby.user.models.User;
import com.teletabist.clubby.user.models.UserRepository;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository uRepository){
        this.userRepository = uRepository;
    }

    public User addPerson(User u){
        try{
            u.setEmail_verification_token(UUID.randomUUID().toString());
            
            //Trim and validate password format
            String _trimmed = u.getPassword().trim();
            if(_trimmed.length()>7 && _trimmed.length()<65){
                u.setPassword(passwordEncoder.encode(_trimmed));
                u = this.userRepository.save(u);
                return u;
            }
        }catch(DataIntegrityViolationException e){
            LoggerFactory.getLogger(UserService.class).error(e.getMessage());
        }
        return null;
    }

    public User getUser(String username){
        return  this.userRepository.findByUsername(username);
    }

    public boolean checkPassword(User u){
        User x = this.getUser(u.getUsername());
        if(x != null){
            return this.passwordEncoder.matches(u.getPassword(), x.getPassword());
        }else{
            return false;
        }
    }

    public Iterable<User> getAll(){
        return this.userRepository.findAll();
    }
}