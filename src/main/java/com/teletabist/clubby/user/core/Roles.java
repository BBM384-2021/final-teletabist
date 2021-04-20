package com.teletabist.clubby.user.core;

public enum Roles {
    NO_ROLE("NO_ROLE"),
    MEMBER("MEMBER"),
    SUB_CLUB_ADMIN("SUB_CLUB_ADMIN"),
    ADMIN("ADMIN"),
    SYS_ADMIN("SYS_ADMIN");

    private final String name;

    private Roles(String name){
        this.name = name;
    }

    public String getRoleName(){
        return "ROLE_"+this.name;
    }

    public String getName(){
        return this.name;
    }

    static public Roles getRoleTypeByName(String name){
        if(name!=null){
            if(!name.isEmpty()){
                if(Roles.MEMBER.getName().equals(name)){
                    return Roles.MEMBER;
                }else if(Roles.SUB_CLUB_ADMIN.getName().equals(name)){
                    return Roles.SUB_CLUB_ADMIN;
                }else if(Roles.ADMIN.getName().equals(name)){
                    return Roles.ADMIN;
                }else if(Roles.SYS_ADMIN.getName().equals(name)){
                    return Roles.SYS_ADMIN;
                }
            }
        }
        return Roles.NO_ROLE;
    }
}