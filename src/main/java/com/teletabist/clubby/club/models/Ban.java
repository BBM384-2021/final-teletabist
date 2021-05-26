package com.teletabist.clubby.club.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.Table;

import com.teletabist.clubby.user.models.UserRole;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "bans")
public class Ban {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_role_id")
    private UserRole userRole;

    @CreationTimestamp
    @Column(name = "start_at")
    private Timestamp startAt;

    @Column
    private Timestamp endAt;

    @ManyToOne
    @JoinColumn(name = "banned_by_user_role")
    private UserRole bannedByUserRole;

    @PostPersist
    public void onCreate() {
        setEndAt(new Timestamp(startAt.getTime() + (5 * 24 * 60 * 60 * 1000)));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Timestamp getStartAt() {
        return startAt;
    }

    public void setStartAt(Timestamp startAt) {
        this.startAt = startAt;
    }

    public Timestamp getEndAt() {
        return endAt;
    }

    public void setEndAt(Timestamp endAt) {
        this.endAt = endAt;
    }

    public UserRole getBannedByUserRole() {
        return bannedByUserRole;
    }

    public void setBannedByUserRole(UserRole bannedByUserRole) {
        this.bannedByUserRole = bannedByUserRole;
    }

    
}
