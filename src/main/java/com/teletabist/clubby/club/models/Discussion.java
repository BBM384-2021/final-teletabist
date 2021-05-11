package com.teletabist.clubby.club.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teletabist.clubby.user.models.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="discussion_messages")
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @JsonIgnoreProperties({"subclubs", "parent",  "clubRating"})
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    @Column(columnDefinition="TEXT")
    private String message;

    @Column(nullable = false, name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "target_user_id", nullable = true, referencedColumnName = "id")
    private User target_user;

    @Column(nullable = false, name = "seen_at")
    private Timestamp seen_at;

//Getter Setter

	public Integer getId() {
		return id;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getCreated_at() {
		return createdAt;
	}

	public void setCreated_at(Timestamp created_at) {
		this.createdAt = created_at;
	}

	public User getTarget() {
		return target_user;
	}

	public void setTarget(User target_user) {
		this.target_user= target_user;
	}

	public Timestamp getSeen_at() {
		return seen_at;
	}

	public void setSeen_at(Timestamp seen_at) {
		this.seen_at = seen_at;
	}

	
}