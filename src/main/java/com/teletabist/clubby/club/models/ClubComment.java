package com.teletabist.clubby.club.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teletabist.clubby.user.models.User;
import com.teletabist.clubby.user.models.UserRole;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * RESOURCES:
 * 1-many: https://www.baeldung.com/hibernate-one-to-many
 * complete comment sample: https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-many-mapping-example/
 * simple model sample: https://attacomsian.com/blog/spring-data-jpa-one-to-many-mapping
 */

@Entity
@Table(name="club_comments")
public class ClubComment {

    /**
     * PRIMARY KEY
     *
     * Identifaction number is unique to comment and
     * defined by database solution.
     * {Getter}
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private Integer id;

    /*  !!!!!!  READ ME  !!!!!!

     * The @ManyToOne annotation is used to define a many-to-one relationship between
     * two entities in Spring Data JPA. The child entity, that has the join column (this entity),
     * is called the owner of the relationship defined using the @ManyToOne annotation.
     *
     * The @JoinColumn annotation is used to specify the foreign key column (in the database)
     * in the owner of the relationship.
     */
    /**
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
     * TODO:add (length = 10)
     */
    //@JsonIgnore <--- use if needed
    @JsonIgnoreProperties({"subclubs", "parent",  "clubRating"})
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    /**
     * FOREIGN KEY to USER
     *
     * defined as NOT optional!
     *
     * BIDIRECTIONAL Relationship
     * The inverse-side of the relationship (User) sets the mappedBy attribute to indicate that
     * the relationship is owned by the other (this) entity.
     *
     * @see #user to bind inverse-side using mappedBy attribute.
     * (Important to use the exact name!!!!)
     *
     * {Getter, Setter}
     * TODO: add (length = 10)
     */
    //@JsonIgnore <--- use if needed
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * The literal comment of the user
     * {Getter, Setter}
     */
    @Column(columnDefinition="TEXT")
    private String comment;

    /**
     * The status of the liking
     * {Getter, Setter}
     */
    @Column(columnDefinition = "TINYINT(1)")
    private boolean liked;

    /**
     * Creation time of the comment
     * {Getter}
     */
    @Column(nullable = false, name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    /**
     * Last time the comment is updated
     * {Getter, Setter}
     */
    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updated_at;

    /**
     * The status of the visibility
     * Shows whether the comment will be visible to users or not
     * {Getter, Setter}
     */
    @Column(columnDefinition = "TINYINT(1)")
    private boolean visible;

    /**
     * FOREIGN KEY to USER_ROLE
     *
     * defined as optional!
     *
     * BIDIRECTIONAL Relationship
     * The inverse-side of the relationship (UserRole) sets the mappedBy attribute to indicate that
     * the relationship is owned by the other (this) entity.
     *
     * @see #userRole to bind inverse-side using mappedBy attribute.
     * (Important to use the exact name!!!!)
     *
     * The authorized person that last updated the comment
     * Will state to role of the person as "Updated by xxxx"
     * {Getter, Setter}
     * TODO:Open when the implementations are done! and add (length = 10)
     */
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "updated_by", nullable = false)
    //private UserRole userRole;


    //------GETTERS and SETTERS---------

    /**
     * No setter for id since it should not be changed!
     */
    public Integer getId() {
        return id;
    }

    //TODO:Open when the implementations are done! Don't forget to update ClubCommentService
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

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isLiked() {
        return liked;
    }
    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    /**
     * No setter for created_at since it should not be changed!
     */
    public Timestamp getCreated_at() {
        return createdAt;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }
    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isVisible() {
        return visible;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /*TODO:Open when the implementations are done!
    public UserRole getUserRole() {
        return userRole;
    }
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
    */
}