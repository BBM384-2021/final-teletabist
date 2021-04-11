package com.teletabist.clubby.club.roles;

import com.teletabist.clubby.club.core.ClubRole;

public class ClubSubAdminRole extends ClubRole{

    public ClubSubAdminRole() {
        super("SUB_ADMIN", "club:view", "club:update", "club:public_mess", "club:private_mess", "club:create_activity", "club:join_activity");
    }
    
}
