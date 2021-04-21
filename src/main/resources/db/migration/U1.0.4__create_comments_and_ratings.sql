ALTER TABLE club_comments DROP FOREIGN KEY `comment on`;
ALTER TABLE club_comments DROP FOREIGN KEY `comment of`;
ALTER TABLE club_comments DROP FOREIGN KEY `updated by`;
ALTER TABLE club_ratings DROP FOREIGN KEY `rating of`;
DROP TABLE IF EXISTS club_comments;
DROP TABLE IF EXISTS club_ratings;