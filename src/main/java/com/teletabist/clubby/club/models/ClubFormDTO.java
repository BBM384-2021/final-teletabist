package com.teletabist.clubby.club.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class ClubFormDTO {
    @Size(max = 144)
    private String slug;

    @NotNull
    @NotEmpty
    @Size(max = 255, message = "Name can have at most 255 characters.")
    private String name;
    
    @NotNull
    private Integer parent_id;

    @Size(max = 512)
    private String description;

    public ClubFormDTO(){

    }

    public ClubFormDTO(Club c){
        this.name = c.getName();
        if(c.getParent() != null)
            this.parent_id = c.getParent().getId();
        else
            this.parent_id = -1;
        this.description = c.getDescription();
        this.location = c.getLocation();
        this.slug = c.getSlug();
        this.website = c.getWebsite();
    }

    public String getSlug() {
        if(this.slug != null) 
            if(this.slug.isEmpty()) return null;
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Size(max = 256)
    private String website;

    @Size(max = 512)
    private String location;

    private MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }



}
