-- Create clubs table
CREATE TABLE clubs(
    club_id int(10) NOT NULL AUTO_INCREMENT,
    club_slug varchar(144) NOT NULL UNIQUE,
    club_name varchar(255) NOT NULL,
    club_description TEXT,
    club_profile_photo_url varchar(1024),
    club_website varchar(256),
    club_location TEXT,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    parent_club_id int(10),
    PRIMARY KEY(club_id)
);