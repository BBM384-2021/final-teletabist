-- Create users table
CREATE TABLE users(
    id int(10) NOT NULL CREATE INDEX AUTO_INCREMENT=1,
    username varchar(32) NOT NULL UNIQUE,
    email varchar(255) NOT NULL UNIQUE,
    pass varchar(255) NOT NULL,
    pass_reset varchar(255),
    pass_reset_end timestamp,
    email_ver_token varchar(255) NOT NULL,
    verified_at timestamp,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
);

-- Create Profiles Table
CREATE TABLE profiles(
    id int(10) NOT NULL CREATE INDEX AUTO_INCREMENT=1,
    user_id int(10) NOT NULL CREATE INDEX UNIQUE,
    name varchar(255) NOT NULL,
    profile_photo_url varchar(1024),
    biography varchar(512),
    birthday date NOT NULL,
    current_location varchar(max),
    gender int(1),
    institution varchar(max),
    job_title varchar(max),
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users,
);

