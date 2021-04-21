package com.teletabist.clubby.club.models;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="club_ratings")
public class ClubRating {

    /**
     * PRIMARY KEY
     * FOREIGN KEY to CLUB
     *
     * defined as NOT optional!
     *
     * BIDIRECTIONAL Relationship
     * The inverse-side of the relationship (Club) sets the mappedBy attribute to indicate that
     * the relationship is owned by the other (this) entity.
     *
     * @see #club to bind inverse-side using mappedBy attribute.
     * (Important to use the exact name!!!!)
     *
     * {Getter, Setter}
     */
    //@JoinColumn(name = "club_id")
    //@JsonIgnore <--- use if needed
    @Id
    private Integer club_id;
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId
    private Club club;

    /**
     * The number of total likes
     * {Getter, Setter}
     */
    @Column(length = 11)
    private Integer total_likes = 0;

    /**
     * The number of total dislikes
     * {Getter, Setter}
     */
    @Column(length = 11)
    private Integer total_dislikes = 0;

    /**
     * Creation time of the rating
     * {Getter}
     */
    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp created_at;

    /**
     * Last time the rating is updated
     * {Getter, Setter}
     */
    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updated_at;

    public void addLike() {
        this.total_likes++;
    }
    public void deleteLike() {
        if (total_likes > 0) total_likes--;
        else total_likes = 0;
    }

    public void addDislike() {
        this.total_dislikes++;
    }
    public void deleteDislike() {
        if (total_dislikes > 0) total_dislikes--;
        else total_dislikes = 0;
    }

    //------GETTERS and SETTERS---------

    public Club getClub() {
        return club;
    }
    public void setClub(Club club) {
        this.club = club;
    }

    public Integer getTotal_likes() {
        return total_likes;
    }
    public void setTotal_likes(Integer total_likes) {
        this.total_likes = total_likes;
    }

    public Integer getTotal_dislikes() {
        return total_dislikes;
    }
    public void setTotal_dislikes(Integer total_dislikes) {
        this.total_dislikes = total_dislikes;
    }

    /**
     * No setter for created_at since it should not be changed!
     */
    public Timestamp getCreated_at() {
        return created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }
    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Double getRatio() {
        return this.total_likes / (double) (this.total_likes + this.total_dislikes) * 100.0;
    }

}
