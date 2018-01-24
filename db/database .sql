DROP DATABASE IF EXISTS `chief`;
CREATE DATABASE `chief`;

USE `chief`;

CREATE TABLE IF NOT EXISTS `user` (
	`user_id` INT NOT NULL AUTO_INCREMENT,
	`login` VARCHAR(45) NOT NULL,
	`password` VARCHAR(45) NOT NULL,
	`user_name` VARCHAR(35) NOT NULL,
	PRIMARY KEY (`user_id`),
	CONSTRAINT uniq_login UNIQUE (`login`)
);

CREATE TABLE IF NOT EXISTS `ingredient_categories`(
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(40) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ingredients`(
	`ingredient_id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(75) NOT NULL,
	`category_of_ingredient` INT NOT NULL,
	FOREIGN KEY (`category_of_ingredient`) REFERENCES ingredient_categories(`id`),
	PRIMARY KEY (`ingredient_id`)
);


CREATE TABLE IF NOT EXISTS `dishes`(
	`dish_id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR (75) NOT NULL,
	`details` VARCHAR (300) NOT NULL,
	`cooking_receptie` MEDIUMTEXT,
	`img_src` VARCHAR(100),
	PRIMARY KEY (`dish_id`)
);

CREATE TABLE IF NOT EXISTS `dishToIngredient`(
	`link_id` INT NOT NULL AUTO_INCREMENT,
	`dish_id` INT NOT NULL,
	`ingredient_id` INT NOT NULL,
	PRIMARY KEY (`link_id`),
	FOREIGN KEY (`dish_id`) REFERENCES dishes(`dish_id`),
	FOREIGN KEY (`ingredient_id`) REFERENCES ingredients(`ingredient_id`)
);



CREATE TABLE IF NOT EXISTS `user_dishes`(
	`dish_id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR (75) NOT NULL,
	`details` VARCHAR (300) NOT NULL,
	`cooking_receptie` MEDIUMTEXT,
	`img_src` VARCHAR(100),
	`user_id` INT NOT NULL,
	FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`),
	PRIMARY KEY (`dish_id`)
);