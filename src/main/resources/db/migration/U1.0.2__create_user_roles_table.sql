ALTER TABLE user_roles DROP FOREIGN KEY `has role`;
ALTER TABLE user_roles DROP FOREIGN KEY `has club`;
DROP TABLE IF EXISTS user_roles;