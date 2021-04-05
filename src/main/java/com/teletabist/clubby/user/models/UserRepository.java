package com.teletabist.clubby.user.models;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{
    public User findDistinctByUsername(String username);
}