CREATE TABLE subclub_admin_blacklist(
    id int(10) NOT NULL AUTO_INCREMENT,
    user_id int(10) NOT NULL,
    PRIMARY KEY (id)
);
ALTER TABLE subclub_admin_blacklist ADD CONSTRAINT `has subclub admin to blacklist` FOREIGN KEY (user_id) REFERENCES users (id);