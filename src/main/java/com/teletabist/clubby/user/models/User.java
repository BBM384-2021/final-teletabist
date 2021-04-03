package com.teletabist.clubby.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;

    public User(
            @JsonProperty("id") int id, 
            @JsonProperty("username") String username, 
            @JsonProperty("email") String email, 
            @JsonProperty("password") String password){
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void updateId(int id){
        this.id = id;
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