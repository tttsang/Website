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

CREATE TABLE new_table(
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NULL,
  `title` VARCHAR(45) NULL,
  `author` VARCHAR(45) NULL,
  `auditor` VARCHAR(45) NULL,
  `content` VARCHAR(45) NULL,
  `picture_url` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
