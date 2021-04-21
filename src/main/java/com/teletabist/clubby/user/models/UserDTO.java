package com.teletabist.clubby.user.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 32, message = "Username should have at least 2, at most 32 characters.")
    private String username;

    @NotNull
    @NotEmpty
    @Size(min = 8, max = 64, message =  "Password must have at least 8 characters, at most 64 characters.")
    private String password;
    private String password_validate;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_validate() {
        return password_validate;
    }

    public void setPassword_validate(String password_validate) {
        this.password_validate = password_validate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}