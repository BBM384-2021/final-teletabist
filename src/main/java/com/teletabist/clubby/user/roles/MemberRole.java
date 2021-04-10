package com.teletabist.clubby.user.roles;

import com.teletabist.clubby.user.core.Role;

public class MemberRole extends Role{

    public MemberRole() {
        super("USER_MEMBER", "user:view");
    }
    
}