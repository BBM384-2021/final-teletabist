CREATE TABLE club_roles(
    id int(10) NOT NULL,
    club_id int(10),
    PRIMARY KEY (id)
);
ALTER TABLE club_roles ADD CONSTRAINT `has user role` FOREIGN KEY (id) REFERENCES user_roles (id);
ALTER TABLE club_roles ADD CONSTRAINT `has club id` FOREIGN KEY (club_id) REFERENCES clubs (id);