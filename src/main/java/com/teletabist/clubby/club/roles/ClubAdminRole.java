package com.teletabist.clubby.club.roles;

import com.teletabist.clubby.club.core.ClubRole;

public class ClubAdminRole extends ClubRole {

    public ClubAdminRole() {
        super("CLUB_ADMIN", "club:view", "club:update", "club:create", "club:delete", "club:public_mess", "club:private_mess",  "club:create_activity", "club:join_activity");
    }
    
}