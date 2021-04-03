package com.teletabist.clubby.user.models;

public class User {
    private final int id;
    private final String username;
    private final String email;

    public User(int id, String username, String email){
        this.id = id;
        this.username = username;
        this.email = email;
    }
}