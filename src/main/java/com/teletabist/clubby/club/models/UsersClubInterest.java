package com.teletabist.clubby.club.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.teletabist.clubby.user.models.User;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="users_club_interests")
public class UsersClubInterest {
    @EmbeddedId
    private UsersClubInterestKey id;

    @ManyToOne
    @MapsId("userID")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("clubID")
    @JoinColumn(name = "club_id")
    private Club club;

    @Column(name = "interest_rate")
    private Double interest_rate;

    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updated_at;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp created_at;

    public UsersClubInterest() {
        
    }

    public UsersClubInterestKey getId() {
        return id;
    }

    public void setId(UsersClubInterestKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Double getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(Double interest_rate) {
        this.interest_rate = interest_rate;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UsersClubInterest other = (UsersClubInterest) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}
