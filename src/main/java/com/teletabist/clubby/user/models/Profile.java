package com.teletabist.clubby.user.models;

import java.util.Date;

public class Profile {
    private final String name;
    private final String biography;
    private final Date birthday;
    private final String current_location;
    private final int gender;
    private final String institution;
    private final String job_title;
    private final Date created_at;
    private final Date updated_at;

    public Profile(String name, String biography, Date birthday, String current_location, int gender, String institution, String job_title, Date created_at, Date updated_at){
        this.name = name;
        this.biography = biography;
        this.birthday = birthday;
        this.current_location = current_location;
        this.gender = gender;
        this.institution = institution;
        this.job_title = job_title;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

	public String getName() {
		return name;
	}

	public String getBiography() {
		return biography;
	}

	public Date getBirthday() {
		return birthday;
	}

	public String getCurrent_location() {
		return current_location;
	}

	public int getGender() {
		return gender;
	}

	public String getInstitution() {
		return institution;
	}

	public String getJob_title() {
		return job_title;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}
}