CREATE TABLE club_comments (
id int(10) NOT NULL AUTO_INCREMENT,
club_id int(10) NOT NULL,
user_id int(10) NOT NULL,
comment text,
liked tinyint(1),
created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
visible tinyint(1),
updated_by int(10),
PRIMARY KEY (id));
CREATE TABLE club_ratings (
club_id int(10) NOT NULL,
total_likes int(11),
total_dislikes int(11),
created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (club_id));
ALTER TABLE club_comments ADD CONSTRAINT `comment on` FOREIGN KEY (club_id) REFERENCES clubs(id);
ALTER TABLE club_comments ADD CONSTRAINT `comment of` FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE club_comments ADD CONSTRAINT `updated by` FOREIGN KEY (updated_by) REFERENCES user_roles(id);
ALTER TABLE club_ratings ADD CONSTRAINT `rating of` FOREIGN KEY (club_id) REFERENCES clubs(id);