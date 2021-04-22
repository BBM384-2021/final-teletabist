package com.teletabist.clubby.user.security;

import com.teletabist.clubby.user.SecureUserPrincipal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("profileSecurity")
public class ProfileSecurity {
    public boolean isUser(String username){
        Object p = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(p instanceof String) return false;
        if(p instanceof SecureUserPrincipal){
            SecureUserPrincipal sup = (SecureUserPrincipal) p;
            if(sup.getUsername().equals(username)) return true;
        }
        return false;
    }
}