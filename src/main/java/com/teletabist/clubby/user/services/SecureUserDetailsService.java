package com.teletabist.clubby.user.services;

import com.teletabist.clubby.user.SecureUserPrincipal;
import com.teletabist.clubby.user.models.User;
import com.teletabist.clubby.user.models.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecureUserDetailsService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        User user = userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException(username);
        }
        return new SecureUserPrincipal(user);
    }
}