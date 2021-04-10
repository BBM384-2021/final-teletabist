package com.teletabist.clubby.user.roles;

import com.teletabist.clubby.user.core.Role;

public class AdminRole extends Role{

    public AdminRole() {
        super("USER_ADMIN", "user:read", "user:update", "user:create", "user:delete", "profile:view");
    }
    
}