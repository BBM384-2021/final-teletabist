package com.teletabist.clubby.event.core.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.user.models.User;

import javax.persistence.*;

@Entity
@Table(name="event_attendees")
public class EventAttendee {
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
     * for user_id and user
     * Should this be one-to-one?
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    @JsonBackReference
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    // -------- Getters and Setters ----------

    public Integer getId() {
        return id;
    }

    public Club getClub() {
        return club;
    }
    public void setClub(Club club) {
        this.club = club;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
