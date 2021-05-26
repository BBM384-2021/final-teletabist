package com.teletabist.clubby.event.core.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.user.models.UserRole;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="events")
public class Event {
    //TODO: Check and adjust the right values and implementations!
    /**
     * PRIMARY KEY
     *
     * Identification number is unique
     * {Getter}
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private Integer id;

    /**
     * for club_id and club
     */
    @JsonIgnoreProperties({"subclubs", "parent",  "clubRating"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    /**
     * for user role to get clubrole_id (or userrole_id)
     */
    @JsonManagedReference
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private List<UserRole> roles;

    /**
     * {Getter, Setter}
     */
    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp created_at;

    /**
     * {Getter, Setter}
     */
    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updated_at;

    //Zoom authentication system should be implemented
    //--> here
    //No idea how.


    // -------- Getters and Setters ------------

    public Integer getId() {
        return id;
    }

    public Club getClub() {
        return club;
    }
    public void setClub(Club club) {
        this.club = club;
    }

    public List<UserRole> getRoles() {
        return roles;
    }
    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }
    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}
