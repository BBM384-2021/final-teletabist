package com.teletabist.clubby.user.daos;

import java.util.List;

import com.teletabist.clubby.user.models.User;

public class ListUserDAO implements UserDAO {
    List<User> users;

    @Override
    public User createUser(User user) {
        User newUser = new User(this.users.size(), user);
        this.users.add(newUser);
        return newUser;
    }

    @Override
    public User getUser(int userid) {
        if(userid < this.users.size() || userid >= 0){
            return this.users.get(userid);
        }
        return null;
    }

    @Override
    public User updateUser(User user) {
        // TODO Auto-generated method stub
        return null;
    }
    
}