package com.teletabist.clubby.user.models;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="profiles")
public class Profile {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private Integer id;
	
	@OneToOne(fetch = FetchType.EAGER,optional = false) //relation
	@JsonBackReference 
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(nullable = false, length = 255)
    private String name;


	@Column(nullable = true, length = 1024)
    private String profile_photo_url;

	@Column(nullable = true, length = 512)
    private String biography;

    private Date birthday;

    @Column(nullable = true, length = 255)
    private String current_location;

	@Column(length = 1)
    private Integer gender;

    @Column(nullable = false, length = 255)
    private String institution;

    @Column(nullable = false, length = 255)
    private String  job_title;

	@Column(nullable = false)
    @CreationTimestamp
    private Timestamp created_at;

    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updated_at;


	// Getters and Setters
	
	public Integer getId() {
		return this.id;
	}

	public User getUser() {
		return user;
	}
	
	
	public String getName() {
		return this.name;
	}

	public String getBiography() {
		return this.biography;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public String getCurrent_location() {
		return this.current_location;
	}

	public int getGender() {
		return this.gender;
	}

	public String getInstitution() {
		return this.institution;
	}

	public String getJob_title() {
		return this.job_title;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfile_photo_url() {
		return profile_photo_url;
	}

	public void setProfile_photo_url(String profile_photo_url) {
		this.profile_photo_url = profile_photo_url;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setCurrent_location(String current_location) {
		this.current_location = current_location;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

}