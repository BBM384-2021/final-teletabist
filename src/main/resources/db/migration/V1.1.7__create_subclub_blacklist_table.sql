CREATE TABLE subclub_blacklist(
    id int(10) NOT NULL AUTO_INCREMENT,
    user_id int(10) NOT NULL,
    club_id int(10) NOT NULL,
    PRIMARY KEY (id)
);
ALTER TABLE subclub_blacklist ADD CONSTRAINT `has user to blacklist` FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE subclub_blacklist ADD CONSTRAINT `has club to blacklist` FOREIGN KEY (club_id) REFERENCES clubs (id);