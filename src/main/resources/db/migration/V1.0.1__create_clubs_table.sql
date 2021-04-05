-- Create clubs table
CREATE TABLE clubs(
    id int(10) NOT NULL AUTO_INCREMENT,
    slug varchar(144) NOT NULL UNIQUE,
    name varchar(255) NOT NULL,
    description varchar(MAX),
    profile_photo_url varchar(1024),
    website varchar(256),
    location varchar(MAX),
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    parent_id int(10),
    PRIMARY KEY(id)
);