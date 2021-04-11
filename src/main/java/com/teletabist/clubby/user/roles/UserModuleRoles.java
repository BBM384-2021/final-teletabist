package com.teletabist.clubby.user.roles;

import java.util.ArrayList;
import java.util.HashSet;
import javax.annotation.Priority;

import com.teletabist.clubby.user.core.ModuleRoles;
import com.teletabist.clubby.user.core.Role;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

@Service
@Priority(Ordered.LOWEST_PRECEDENCE)
public class UserModuleRoles implements ModuleRoles {

    private HashSet<Role> roles;

    public UserModuleRoles(){
        this.roles = new HashSet<Role>();
        roles.add(new AdminRole());
        roles.add(new MemberRole());
    }

    @Override
    public boolean supports(String delimiter) {
        
        return delimiter.equals("USER_MODULE");
    }

    @Override
    public ArrayList<String> getRoleNames() {
        ArrayList<String> s = new ArrayList<String>();
        for(Role r : this.roles){
            s.add(r.getName());
        }
        return s;
    }

    @Override
    public Role getRoleByName(String name) {
        for(Role r : this.roles){
            if(r.getName().equals(name))
                return r;
        }
        return null;
    }
    
}