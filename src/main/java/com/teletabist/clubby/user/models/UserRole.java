
package com.teletabist.clubby.user.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teletabist.clubby.user.core.Roles;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "user_roles")
public class UserRole implements GrantedAuthority{
    /**
     *
     */
    private static final long serialVersionUID = -8242444042888842023L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private Integer id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 35, nullable = false)
    private String role;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp assigned_at;

    private Timestamp designed_at;

    @Column(length = 10, name = "club_id")
    private Integer club;

    public Integer getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public Timestamp getAssigned_at() {
        return assigned_at;
    }

    public Timestamp getDesigned_at() {
        return designed_at;
    }

    public void setDesigned_at(Timestamp designed_at) {
        this.designed_at = designed_at;
    }

    public Integer getClub_id() {
        return club;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setClub_id(Integer club_id) {
        this.club = club_id;
    }

    @JsonIgnore
    @Override
    public String getAuthority() {
        return Roles.getRoleTypeByName(this.role).getRoleName();
    }
}