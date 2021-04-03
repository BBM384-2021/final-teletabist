package com.teletabist.clubby.club.models;

public class Club {
    private final int id;
    private final String slug;
    private final String name;
    private final String description;
    private final String profilePhotoURL;
    private final String website;
    private final String location;
    private final int parentId;

    public Club(int id, String slug, String name, String description, String profilePhotoURL, String website,
            String location, int parentId) {
        this.id = id;
        this.slug = slug;
        this.name = name;
        this.description = description;
        this.profilePhotoURL = profilePhotoURL;
        this.website = website;
        this.location = location;
        this.parentId = parentId;
    }
    

    public int getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getProfilePhotoURL() {
        return profilePhotoURL;
    }

    public String getWebsite() {
        return website;
    }

    public String getLocation() {
        return location;
    }

    public int getParentId() {
        return parentId;
    }

    
    
}
