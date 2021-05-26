CREATE TABLE system_contacts (
  id      int(10) NOT NULL AUTO_INCREMENT, 
  user_id int(10) NOT NULL, 
  type    int(10) NOT NULL, 
  message TEXT NOT NULL, 
  priority int(10) NOT NULL, 
  club_id int(10), 
  status int(10) NOT NULL, 
  created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id));

ALTER TABLE system_contacts ADD CONSTRAINT FKsystem_con721437 FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE system_contacts ADD CONSTRAINT FKsystem_con367637 FOREIGN KEY (club_id) REFERENCES clubs (id);

