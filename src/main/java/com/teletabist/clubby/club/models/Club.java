package com.teletabist.clubby.club.models;


import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="clubs")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private Integer id;

    @Column(unique = true, nullable = false, length = 144)
    private String slug;

    @Column(nullable = false, length = 255)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;

    //DEĞİŞEBİLİR
    @Column(length = 1024)
    private String profile_photo_url;

    @Column(length = 256)
    private String website;

    @Column(columnDefinition = "TEXT")
    private String location;


    @JsonIgnoreProperties({"subclubs", "parent"})
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private Collection<Club> subclubs;

    @JsonIgnoreProperties({"subclubs", "parent"})
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Club parent;

    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updated_at;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp created_at;

    @OneToMany(mappedBy = "club")
    private Set<ClubRoles> club_roles;

    /*@OneToMany(mappedBy = "club")
    private Set <UsersClubInterest> interests;*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String _trimmed = name.trim();

        this.name = _trimmed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfile_photo_url() {
        return profile_photo_url;
    }

    public void setProfile_photo_url(String profile_photo_url) {
        this.profile_photo_url = profile_photo_url;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public Collection<Club> getSubclubs() {
        return subclubs;
    }

    public void setSubclubs(Collection<Club> subclubs) {
        this.subclubs = subclubs;
    }

    public Club getParent() {
        return parent;
    }

    public void setParent(Club parent) {
        this.parent = parent;
    }
}
