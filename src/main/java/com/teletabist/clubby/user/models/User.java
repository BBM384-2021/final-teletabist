package com.teletabist.clubby.user.models;

public class User {
    private final int id;
    private final String username;
    private final String email;
    private final String password;

    public User(int id, String username, String email, String password){
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(int id, User u){
        this.id = id;
        this.username = u.getUsername();
        this.email = u.getEmail();
        this.password = u.getEmail();
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}