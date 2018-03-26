CREATE DATABASE computersite;

use computersite;

CREATE TABLE t_user(
	id VARCHAR(25) PRIMARY KEY,
	password VARCHAR(16) NOT NULL
);

INSERT t_user VALUES('admin','admin');

CREATE TABLE t_index_professor(
	id int PRIMARY KEY AUTO_INCREMENT,
    pictureurl varchar(255) NOT NULL,
    name varchar(10)
);

CREATE TABLE t_power_garden(
	id int PRIMARY KEY AUTO_INCREMENT,
  major INTEGER NOT NULL,
  name varchar(10)
);

CREATE TABLE t_article(
  id VARCHAR(45) PRIMARY KEY,
  isShow int,
  time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  title VARCHAR(45),
  author VARCHAR(45),
  auditor VARCHAR(45),
  content VARCHAR(4500),
  picture_url VARCHAR(45)
);

CREATE TABLE t_catalogue(
  id VARCHAR(45),
  first_level VARCHAR(45),
  second_level VARCHAR(45)
);

CREATE TABLE t_situation_leader (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NULL,
  telephone VARCHAR(45) NULL,
  work  VARCHAR(45) NULL,
  location VARCHAR(45) NULL,
  email VARCHAR(45) NULL,
  pictureurl varchar(255) NOT NULL,
  PRIMARY KEY (id));
