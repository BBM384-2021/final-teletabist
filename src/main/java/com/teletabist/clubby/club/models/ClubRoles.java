package com.teletabist.clubby.club.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.teletabist.clubby.user.models.UserRole;


@Entity
@Table(name="club_roles")
public class ClubRoles {
    @Id
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private UserRole user_role;

    @ManyToOne
    @JoinColumn(name = "club_id", referencedColumnName = "id")
    private Club club;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserRole getUser_role() {
        return user_role;
    }

    public void setUser_role(UserRole user_role) {
        this.user_role = user_role;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}