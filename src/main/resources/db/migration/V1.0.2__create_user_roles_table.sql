CREATE TABLE user_roles(
    id int(10) NOT NULL AUTO_INCREMENT,
    user_id int(10) NOT NULL,
    role varchar(35) NOT NULL,
    assigned_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    designed_at timestamp NULL,
    club_id int(10), 
    PRIMARY KEY (id)
);
ALTER TABLE user_roles ADD CONSTRAINT `has role` FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE user_roles ADD CONSTRAINT `has club` FOREIGN KEY (club_id) REFERENCES clubs (id);