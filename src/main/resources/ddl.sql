DROP DATABASE IF EXISTS forum;
CREATE DATABASE forum;

USE forum;

SET GLOBAL FOREIGN_KEY_CHECKS=0;

-- Usuarios para login

CREATE TABLE roles (
                       id INT AUTO_INCREMENT,
                       name VARCHAR(255),
                       CONSTRAINT pk_roles_id PRIMARY KEY (id)
);

CREATE TABLE users (
                       id INT AUTO_INCREMENT,
                       enabled BOOLEAN,
                       username VARCHAR(12) UNIQUE,
                       password VARCHAR(255),
                       CONSTRAINT pk_users_id PRIMARY KEY (id)
);

CREATE TABLE users_roles (
                             role_id INT,
                             user_id INT,
                             CONSTRAINT pk_users_roles_role_id_user_id PRIMARY KEY (role_id, user_id),
                             CONSTRAINT fk_users_roles_role_id FOREIGN KEY (role_id) REFERENCES roles(id),
                             CONSTRAINT fk_users_roles_user_id FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Formularios y Respuestas

CREATE TABLE surveys (
                         id INT AUTO_INCREMENT,
                         created_at TIMESTAMP(6),
                         updated_at TIMESTAMP(6),
                         description VARCHAR(255),
                         name VARCHAR(255),
                         CONSTRAINT pk_surveys_id PRIMARY KEY (id)
);

CREATE TABLE chapter (
                         id INT AUTO_INCREMENT,
                         created_at TIMESTAMP(6),
                         updated_at TIMESTAMP(6),
                         chapter_number VARCHAR(50),
                         chapter_title VARCHAR(50),
                         survey_id INT,
                         CONSTRAINT pk_chapter_id PRIMARY KEY (id),
                         CONSTRAINT fk_chapter_survey_id FOREIGN KEY (survey_id) REFERENCES surveys(id)
);

CREATE TABLE questions (
                           id INT AUTO_INCREMENT,
                           created_at TIMESTAMP(6),
                           updated_at TIMESTAMP(6),
                           question_number VARCHAR(10),
                           response_type VARCHAR(20),
                           comment_question TEXT,
                           question_text TEXT,
                           chapter_id INT,
                           CONSTRAINT pk_questions_id PRIMARY KEY (id),
                           CONSTRAINT fk_questions_chapter_id FOREIGN KEY (chapter_id) REFERENCES chapter(id)
);

CREATE TABLE categories_catalog (
                                    id INT AUTO_INCREMENT,
                                    created_at TIMESTAMP(6),
                                    updated_at TIMESTAMP(6),
                                    name VARCHAR(255),
                                    CONSTRAINT pk_categories_catalog_id PRIMARY KEY (id)
);

CREATE TABLE response_options (
                                  id INT AUTO_INCREMENT,
                                  option_value INT,
                                  categorycatalog_id INT,
                                  created_at TIMESTAMP(6),
                                  updated_at TIMESTAMP(6),
                                  parentresponse_id INT,
                                  question_id INT,
                                  typecomponenthtml VARCHAR(30),
                                  comment_response TEXT,
                                  option_text TEXT,
                                  CONSTRAINT pk_response_options_id PRIMARY KEY (id),
                                  CONSTRAINT fk_response_options_categorycatalog_id FOREIGN KEY (categorycatalog_id) REFERENCES categories_catalog(id),
                                  CONSTRAINT fk_response_options_question_id FOREIGN KEY (question_id) REFERENCES questions(id),
                                  CONSTRAINT fk_response_options_parentresponse_id FOREIGN KEY (parentresponse_id) REFERENCES response_options(id)
);

CREATE TABLE subresponse_options (
                                     id INT AUTO_INCREMENT,
                                     subresponse_number INT,
                                     created_at TIMESTAMP(6),
                                     updated_at TIMESTAMP(6),
                                     component_html VARCHAR(255),
                                     subresponse_text VARCHAR(255),
                                     responseoptions_id INT,
                                     CONSTRAINT pk_subresponse_options_id PRIMARY KEY (id),
                                     CONSTRAINT fk_subresponse_options_responseoptions_id FOREIGN KEY (responseoptions_id) REFERENCES response_options(id)
);

CREATE TABLE response_question (
                                   id INT AUTO_INCREMENT,
                                   response_id INT,
                                   subresponses_id INT,
                                   responsetext VARCHAR(80),
                                   CONSTRAINT pk_response_question_id PRIMARY KEY (id),
                                   CONSTRAINT fk_response_question_response_id FOREIGN KEY (response_id) REFERENCES response_options(id),
                                   CONSTRAINT fk_response_question_subresponses_id FOREIGN KEY (subresponses_id) REFERENCES subresponse_options(id)
);

CREATE TABLE survey_json (
                             id INT AUTO_INCREMENT,
                             created_at TIMESTAMP(6),
                             updated_at TIMESTAMP(6),
                             survey_id INT,
                             payload JSON,
                             CONSTRAINT pk_survey_json_id PRIMARY KEY (id),
                             CONSTRAINT fk_survey_json_survey_id FOREIGN KEY (survey_id) REFERENCES surveys(id)
);

INSERT INTO surveys (name, description, created_at, updated_at)
VALUES ('Example Survey', 'Survey to try the stuff', NOW(), NOW());

INSERT INTO roles (name) VALUES ('admin');
INSERT INTO roles (name) VALUES ('user');
INSERT INTO users (enabled, username, password) VALUES (true, 'jholver', 'thekiller');
INSERT INTO users (enabled, username, password) VALUES (true, 'andrey', '070905123');
INSERT INTO users_roles (role_id, user_id) VALUES (1,1);
INSERT INTO users_roles (role_id, user_id) VALUES (2,2);

-- Insert the basketball survey
INSERT INTO surveys (created_at, updated_at, description, name)
VALUES (NOW(), NOW(), 'A survey about basketball preferences', 'Basketball Survey');

-- Get the survey ID
SET @survey_id = LAST_INSERT_ID();

-- Insert Chapter 1
INSERT INTO chapter (created_at, updated_at, chapter_number, chapter_title, survey_id)
VALUES (NOW(), NOW(), '1', 'Favorite Teams', @survey_id);

-- Get Chapter 1 ID
SET @chapter1_id = LAST_INSERT_ID();

-- Insert Chapter 2
INSERT INTO chapter (created_at, updated_at, chapter_number, chapter_title, survey_id)
VALUES (NOW(), NOW(), '2', 'Favorite Players', @survey_id);

-- Get Chapter 2 ID
SET @chapter2_id = LAST_INSERT_ID();

-- Insert Chapter 3
INSERT INTO chapter (created_at, updated_at, chapter_number, chapter_title, survey_id)
VALUES (NOW(), NOW(), '3', 'Basketball Experience', @survey_id);

-- Get Chapter 3 ID
SET @chapter3_id = LAST_INSERT_ID();

-- Insert questions for Chapter 1
INSERT INTO questions (created_at, updated_at, question_number, response_type, comment_question, question_text, chapter_id)
VALUES (NOW(), NOW(), '1', 'radio', 'Choose your favorite team.', 'What is your favorite basketball team?', @chapter1_id);

INSERT INTO questions (created_at, updated_at, question_number, response_type, comment_question, question_text, chapter_id)
VALUES (NOW(), NOW(), '2', 'text', 'Explain why you like this team.', 'Why do you support this team?', @chapter1_id);

-- Insert questions for Chapter 2
INSERT INTO questions (created_at, updated_at, question_number, response_type, comment_question, question_text, chapter_id)
VALUES (NOW(), NOW(), '1', 'radio', 'Choose your favorite player.', 'Who is your favorite basketball player?', @chapter2_id);

INSERT INTO questions (created_at, updated_at, question_number, response_type, comment_question, question_text, chapter_id)
VALUES (NOW(), NOW(), '2', 'text', 'Explain why you like this player.', 'Why do you support this player?', @chapter2_id);

-- Insert questions for Chapter 3
INSERT INTO questions (created_at, updated_at, question_number, response_type, comment_question, question_text, chapter_id)
VALUES (NOW(), NOW(), '1', 'radio', 'Choose your playing position.', 'What position do you play in basketball?', @chapter3_id);

INSERT INTO questions (created_at, updated_at, question_number, response_type, comment_question, question_text, chapter_id)
VALUES (NOW(), NOW(), '2', 'text', 'Describe your experience with basketball.', 'How long have you been playing basketball?', @chapter3_id);

-- Insert category for response options
INSERT INTO categories_catalog (created_at, updated_at, name) VALUES (NOW(), NOW(), 'Basketball Preferences');

-- Get the category ID
SET @category_id = LAST_INSERT_ID();

-- Insert response options for Chapter 1, Question 1
INSERT INTO response_options (option_value, categorycatalog_id, created_at, updated_at, parentresponse_id, question_id, typecomponenthtml, comment_response, option_text)
VALUES (1, @category_id, NOW(), NOW(), NULL, (SELECT id FROM questions WHERE question_number='1' AND chapter_id=@chapter1_id), 'radio', 'Choose Lakers', 'Los Angeles Lakers');

INSERT INTO response_options (option_value, categorycatalog_id, created_at, updated_at, parentresponse_id, question_id, typecomponenthtml, comment_response, option_text)
VALUES (2, @category_id, NOW(), NOW(), NULL, (SELECT id FROM questions WHERE question_number='1' AND chapter_id=@chapter1_id), 'radio', 'Choose Warriors', 'Golden State Warriors');

INSERT INTO response_options (option_value, categorycatalog_id, created_at, updated_at, parentresponse_id, question_id, typecomponenthtml, comment_response, option_text)
VALUES (3, @category_id, NOW(), NOW(), NULL, (SELECT id FROM questions WHERE question_number='1' AND chapter_id=@chapter1_id), 'radio', 'Choose Bulls', 'Chicago Bulls');

-- Get the ID of the first response option
SET @response_option1_id = LAST_INSERT_ID();

-- Insert subresponse options for one response option
INSERT INTO subresponse_options (created_at, updated_at, subresponse_number, component_html, subresponse_text, responseoptions_id)
VALUES (NOW(), NOW(), 1, 'checkbox', '1980s Era', @response_option1_id);

INSERT INTO subresponse_options (created_at, updated_at, subresponse_number, component_html, subresponse_text, responseoptions_id)
VALUES (NOW(), NOW(), 2, 'checkbox', '2000s Era', @response_option1_id);

-- Get the ID of the first subresponse option
SET @subresponse_option1_id = LAST_INSERT_ID();

-- Insert response options for Chapter 2, Question 1
INSERT INTO response_options (option_value, categorycatalog_id, created_at, updated_at, parentresponse_id, question_id, typecomponenthtml, comment_response, option_text)
VALUES (1, @category_id, NOW(), NOW(), NULL, (SELECT id FROM questions WHERE question_number='1' AND chapter_id=@chapter2_id), 'radio', 'Choose LeBron James', 'LeBron James');

INSERT INTO response_options (option_value, categorycatalog_id, created_at, updated_at, parentresponse_id, question_id, typecomponenthtml, comment_response, option_text)
VALUES (2, @category_id, NOW(), NOW(), NULL, (SELECT id FROM questions WHERE question_number='1' AND chapter_id=@chapter2_id), 'radio', 'Choose Michael Jordan', 'Michael Jordan');

INSERT INTO response_options (option_value, categorycatalog_id, created_at, updated_at, parentresponse_id, question_id, typecomponenthtml, comment_response, option_text)
VALUES (3, @category_id, NOW(), NOW(), NULL, (SELECT id FROM questions WHERE question_number='1' AND chapter_id=@chapter2_id), 'radio', 'Choose Stephen Curry', 'Stephen Curry');

-- Insert response options for Chapter 3, Question 1
INSERT INTO response_options (option_value, categorycatalog_id, created_at, updated_at, parentresponse_id, question_id, typecomponenthtml, comment_response, option_text)
VALUES (1, @category_id, NOW(), NOW(), NULL, (SELECT id FROM questions WHERE question_number='1' AND chapter_id=@chapter3_id), 'radio', 'Choose Point Guard', 'Point Guard');

INSERT INTO response_options (option_value, categorycatalog_id, created_at, updated_at, parentresponse_id, question_id, typecomponenthtml, comment_response, option_text)
VALUES (2, @category_id, NOW(), NOW(), NULL, (SELECT id FROM questions WHERE question_number='1' AND chapter_id=@chapter3_id), 'radio', 'Choose Shooting Guard', 'Shooting Guard');

INSERT INTO response_options (option_value, categorycatalog_id, created_at, updated_at, parentresponse_id, question_id, typecomponenthtml, comment_response, option_text)
VALUES (3, @category_id, NOW(), NOW(), NULL, (SELECT id FROM questions WHERE question_number='1' AND chapter_id=@chapter3_id), 'radio', 'Choose Center', 'Center');
