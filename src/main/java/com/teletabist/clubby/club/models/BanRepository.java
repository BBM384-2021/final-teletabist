package com.teletabist.clubby.club.models;

import java.util.Collection;

import com.teletabist.clubby.user.models.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BanRepository extends JpaRepository<Ban, Integer>{
    public Collection<Ban> findAllByUserRole(UserRole userRole);
    public Integer deleteAllByUserRole(UserRole userRole);
}
