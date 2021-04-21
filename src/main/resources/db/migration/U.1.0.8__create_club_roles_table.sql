ALTER TABLE club_roles DROP FOREIGN KEY `has user role`;
ALTER TABLE club_roles DROP FOREIGN KEY `has club id`;
DROP TABLE IF EXISTS club_roles;