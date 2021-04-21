CREATE TABLE profiles (
  id                int(10) NOT NULL AUTO_INCREMENT, 
  user_id           int(10) NOT NULL UNIQUE, 
  name              varchar(255) NOT NULL, 
  profile_photo_url varchar(1024), 
  biography         varchar(512), 
  birthday          date, 
  current_location  varchar(255), 
  gender            int(1), 
  institution       varchar(255), 
  job_title         varchar(255), 
  created_at        timestamp NOT NULL, 
  updated_at        timestamp NOT NULL, 
  PRIMARY KEY (id));
  ALTER TABLE profiles ADD CONSTRAINT FKprofiles815537 FOREIGN KEY (user_id) REFERENCES users (id);
