CREATE TABLE survey_questions (
id int(10) NOT NULL AUTO_INCREMENT,
survey_id int(10) NOT NULL,
question_type int(10) NOT NULL,
question_message varchar(255) NOT NULL,
answers varchar(255) NOT NULL,
weight numeric(19, 0),
PRIMARY KEY (id));
CREATE TABLE surveys (
survey_id int(10) NOT NULL AUTO_INCREMENT,
club_id int(10) NOT NULL,
PRIMARY KEY (survey_id));
CREATE TABLE user_surveys (
id int(11) NOT NULL AUTO_INCREMENT,
usersid int(10) NOT NULL,
state int(2) NOT NULL,
answers varchar(255) NOT NULL,
PRIMARY KEY (id));
CREATE TABLE user_surveys_taken (
id int(10) NOT NULL AUTO_INCREMENT,
usersid int(10) NOT NULL,
surveyid int(10) NOT NULL,
usersurveysid int(11) NOT NULL,
PRIMARY KEY (id));
ALTER TABLE survey_questions ADD CONSTRAINT FKsurvey_que725756 FOREIGN KEY (survey_id) REFERENCES surveys (survey_id);
ALTER TABLE surveys ADD CONSTRAINT FKsurveys421057 FOREIGN KEY (club_id) REFERENCES clubs (id);
ALTER TABLE user_surveys_taken ADD CONSTRAINT FKuser_surve722924 FOREIGN KEY (usersid) REFERENCES users (id);
ALTER TABLE user_surveys_taken ADD CONSTRAINT FKuser_surve736056 FOREIGN KEY (surveyid) REFERENCES surveys (survey_id);
ALTER TABLE user_surveys_taken ADD CONSTRAINT FKuser_surve826212 FOREIGN KEY (usersurveysid) REFERENCES user_surveys (id);
ALTER TABLE user_surveys ADD CONSTRAINT `has surveys` FOREIGN KEY (usersid) REFERENCES users (id);