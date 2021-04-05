-- Create users table
CREATE TABLE users(
    id int(10) NOT NULL AUTO_INCREMENT,
    username varchar(32) NOT NULL UNIQUE,
    email varchar(255) NOT NULL UNIQUE,
    password varchar(255) NOT NULL,
    password_reset_token varchar(255),
    password_reset_end timestamp,
    email_verification_token varchar(255) NOT NULL,
    verified_at timestamp,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
);

