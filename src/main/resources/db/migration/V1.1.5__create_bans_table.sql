CREATE TABLE bans(
    id int(10) NOT NULL AUTO_INCREMENT,
    user_role_id int(10) NOT NULL,
    start_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    end_at timestamp NULL,
    banned_by_user_role int(10) NOT NULL,
    PRIMARY KEY (id)
);
ALTER TABLE bans ADD CONSTRAINT `has banned user role` FOREIGN KEY (user_role_id) REFERENCES user_roles (id);
ALTER TABLE bans ADD CONSTRAINT `has banned by user role` FOREIGN KEY (banned_by_user_role) REFERENCES user_roles (id);