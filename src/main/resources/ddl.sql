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
