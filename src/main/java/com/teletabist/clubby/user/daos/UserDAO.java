package com.teletabist.clubby.user.daos;

import com.teletabist.clubby.user.models.User;

public interface UserDAO {
    User createUser(User user);
    User getUser(int userid);
    User updateUser(User user);
}