ALTER TABLE discussion_messages DROP FOREIGN KEY `has incoming msg`;
ALTER TABLE discussion_messages DROP FOREIGN KEY `has message`;
ALTER TABLE discussion_messages DROP FOREIGN KEY `list message`;



DROP TABLE IF EXISTS discussion_messages;