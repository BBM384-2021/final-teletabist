package com.teletabist.clubby.user.daos;

import java.util.ArrayList;
import java.util.List;

import com.teletabist.clubby.user.models.User;

import org.springframework.stereotype.Repository;

@Repository("fakelist")
public class ListUserDAO implements UserDAO {
    private List<User> users;
    private int counter = 0;
    public ListUserDAO(){
            this.users = new ArrayList<User>();
    }

    @Override
    public User createUser(User user) {
        User newUser = new User(
            counter++, 
            user.getUsername(), 
            user.getEmail(), 
            user.getPassword());
        this.users.add(newUser);
        return newUser;
    }

    @Override
    public User getUser(int userid) {
        if (userid < this.users.size() || userid >= 0) {
            return this.users.get(userid);
        }
        return null;
    }

    @Override
    public User updateUser(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> all() {
        return this.users;
    }
    
}