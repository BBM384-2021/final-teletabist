ALTER TABLE users_club_interests DROP FOREIGN KEY `has user`;
ALTER TABLE users_club_interests DROP FOREIGN KEY `has interested club`;
DROP TABLE IF EXISTS users_club_interests;