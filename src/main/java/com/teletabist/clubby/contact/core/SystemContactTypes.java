package com.teletabist.clubby.contact.core;

public enum SystemContactTypes {
    INVALID(0, null, null),
    SYSTEM(1, "System report", "Report failures and bugs in system."), 
    CLUB_REQUEST(2, "Request new club/sub-clubs", "Request new club/sub-clubs."), 
    REPORT_VIOLATION(3, "Report a violation", "Report a community violation.");

    public final int type;
    public final String title;
    public final String description;

    private SystemContactTypes(int type, String title, String description){
        this.type = type;
        this.title = title;
        this.description = description;
    }

    public static SystemContactTypes valueOf(int i){
        for (SystemContactTypes type : SystemContactTypes.values()) {
            if(type.type == i) return type;
        }
        return SystemContactTypes.INVALID;
    }
}