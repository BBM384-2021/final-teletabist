package com.teletabist.clubby.club.roles;

import com.teletabist.clubby.club.core.ClubRole;

public class ClubMemberRole extends ClubRole{
    public ClubMemberRole() {
        super("CLUB_MEMBER", "club:view", "club:public_mess", "club:private_mess", "club:join_activity");
    }
}
