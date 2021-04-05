package com.teletabist.clubby.club.models;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private Integer club_id;

    @Column(unique = true, nullable = false, length = 144)
    private String club_slug;

    @Column(nullable = false, length = 255)
    private String club_name;
    
    @Column(columnDefinition = "TEXT")
    private String club_description = "";

    //DEĞİŞEBİLİR
    @Column(length = 1024)
    private String profile_photo_url;

    @Column(length = 256)
    private String club_website;

    @Column(columnDefinition = "TEXT")
    private String club_location;

    @Column(length = 10)
    private Integer parent_club_id;

    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updated_at;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp created_at;

    public Integer getClub_id() {
        return club_id;
    }

    public void setClub_id(Integer club_id) {
        this.club_id = club_id;
    }

    public String getClub_slug() {
        return club_slug;
    }

    public void setClub_slug(String club_slug) {
        this.club_slug = club_slug;
    }

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }

    public String getClub_description() {
        return club_description;
    }

    public void setClub_description(String club_description) {
        this.club_description = club_description;
    }

    public String getProfile_photo_url() {
        return profile_photo_url;
    }

    public void setProfile_photo_url(String profile_photo_url) {
        this.profile_photo_url = profile_photo_url;
    }

    public String getClub_website() {
        return club_website;
    }

    public void setClub_website(String club_website) {
        this.club_website = club_website;
    }

    public String getClub_location() {
        return club_location;
    }

    public void setClub_location(String club_location) {
        this.club_location = club_location;
    }

    public Integer getParent_club_id() {
        return parent_club_id;
    }

    public void setParent_club_id(Integer parent_club_id) {
        this.parent_club_id = parent_club_id;
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


    
    
}
