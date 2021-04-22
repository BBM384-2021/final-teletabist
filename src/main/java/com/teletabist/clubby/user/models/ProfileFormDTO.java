package com.teletabist.clubby.user.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProfileFormDTO {
    @NotNull
    @NotEmpty
    private String name;

    // Generate getters and setters
    // @NotNull
    // @NotEmpty
    // @Size(min = 2, max = 32, message = "Username should have at least 2, at most 32 characters.")
    // private String username;

    @Size(max = 255, message = "Location can have at most 255 characters.")
    private String location;

    private String birthday;

    @Size(max = 512)
    private String biography;

    private Integer gender;

    @Size(max = 255)
    private String institution;

    @Size(max = 255)
    private String jobTitle;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String job_title) {
        this.jobTitle = job_title;
    }

    public Date getBirthday() {
        if(this.birthday == null) return null;
        Date d = null;
        try{
            d = new SimpleDateFormat("yyyy-MM-dd").parse(this.birthday);
        }catch(ParseException e){
            d = null;
        }
        return d;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }



}