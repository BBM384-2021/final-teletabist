package com.teletabist.clubby.club.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;

import javax.persistence.Table;


@Entity
@Table(name="club_roles")
public class ClubRoles {
    
    @Id
    @Column(length = 10, nullable = false, unique = true)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "club_id", referencedColumnName = "id")
    private Club club;
}