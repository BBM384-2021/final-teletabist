CREATE TABLE discussion_messages (
id int(10) NOT NULL AUTO_INCREMENT,
user_id int(10) NOT NULL,
club_id int(10) NOT NULL,
message varchar(255) NOT NULL,
created_at timestamp NOT NULL,
target_user_id int(10),
seen_at timestamp NULL,
PRIMARY KEY (id));

ALTER TABLE discussion_messages ADD CONSTRAINT `has incoming msg` FOREIGN KEY (target_user_id) REFERENCES users (id);
ALTER TABLE discussion_messages ADD CONSTRAINT `has message` FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE discussion_messages ADD CONSTRAINT `list message` FOREIGN KEY (club_id) REFERENCES clubs (id);
