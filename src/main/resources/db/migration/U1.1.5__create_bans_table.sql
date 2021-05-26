ALTER TABLE bans DROP FOREIGN KEY `has banned user role`;
ALTER TABLE bans DROP FOREIGN KEY `has banned by user role`;
DROP TABLE IF EXISTS bans;