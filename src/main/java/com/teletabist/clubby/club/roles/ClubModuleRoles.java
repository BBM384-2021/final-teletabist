package com.teletabist.clubby.club.roles;

import java.util.ArrayList;
import java.util.HashSet;
import javax.annotation.Priority;

import com.teletabist.clubby.club.core.ClubCoreModuleRoles;
import com.teletabist.clubby.club.core.ClubRole;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

@Service
@Priority(Ordered.LOWEST_PRECEDENCE)
public class ClubModuleRoles implements ClubCoreModuleRoles{

    private HashSet<ClubRole> roles;

    public ClubModuleRoles() {
        this.roles = new HashSet<ClubRole>();
        roles.add(new ClubAdminRole());
        roles.add(new ClubSubAdminRole());
        roles.add(new ClubMemberRole());
    }

    @Override
    public boolean supports(String delimiter) {
        return delimiter.equals("CLUB_MODULE");
    }

    @Override
    public ArrayList<String> getRoleNames() {
        ArrayList<String> names = new ArrayList<String>();
        for(ClubRole role : this.roles){
            names.add(role.getName());
        }
        return names;
    }

    @Override
    public ClubRole getRoleByName(String name) {
        for(ClubRole role : this.roles){
            if(role.getName().equals(name))
                return role;
        }
        return null;
    }
    
}
