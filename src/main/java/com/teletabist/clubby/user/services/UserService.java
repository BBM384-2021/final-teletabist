package com.teletabist.clubby.user.services;

import com.teletabist.clubby.user.daos.UserDAO;
import com.teletabist.clubby.user.models.User;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public User addPerson(User u){
        return this.userDAO.createUser(u);
    }
}