CREATE TABLE users_club_interests(
    id int(10) NOT NULL,
    user_id int(10) NOT NULL,
    club_id int(10) NOT NULL,
    interest_rate DOUBLE PRECISION NULL,
    assigned_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp NULL, 
    PRIMARY KEY (id)
);
ALTER TABLE users_club_interests ADD CONSTRAINT `has user` FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE users_club_interests ADD CONSTRAINT `has interested club` FOREIGN KEY (club_id) REFERENCES clubs (id);