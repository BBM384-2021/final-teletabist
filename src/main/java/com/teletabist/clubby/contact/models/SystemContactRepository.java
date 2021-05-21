package com.teletabist.clubby.contact.models;

import java.util.Collection;

import com.teletabist.clubby.contact.core.SystemContactStatus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemContactRepository extends JpaRepository<SystemContact, Integer>{
    public Collection<SystemContact> findByStatus(SystemContactStatus status);
}