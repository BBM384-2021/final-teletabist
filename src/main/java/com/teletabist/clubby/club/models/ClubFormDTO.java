package com.teletabist.clubby.club.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClubFormDTO {
    @Size(max = 144)
    private String slug;

    @NotNull
    @NotEmpty
    @Size(max = 255, message = "Name can have at most 255 characters.")
    private String name;
    
    /**
     * TO-DO change the max size of descirption
     */
    @Size(max = 512)
    private String description;

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

}
