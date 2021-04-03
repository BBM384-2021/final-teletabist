package com.teletabist.clubby.user.services;

import java.util.List;

import com.teletabist.clubby.user.daos.UserDAO;
import com.teletabist.clubby.user.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(@Qualifier("fakelist") UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public User addPerson(User u){
        return this.userDAO.createUser(u);
    }

    public List<User> getAll(){
        return this.userDAO.all();
    }
}