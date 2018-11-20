CREATE DATABASE sampledb;

USE sampledb;

CREATE TABLE tb_user
(
  username VARCHAR(50) primary key,
  `password` VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL
);

#Make sure we have a starting user with login credentials
INSERT INTO tb_user VALUES ('john', 'pass1234', 'john@john.com');

CREATE TABLE `author` (
  `email` varchar(100) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `affiliation` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`email`)
);

CREATE TABLE `paper` (
  `paperid` INT AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `Abstract` varchar(250) DEFAULT NULL,
  `pdf` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`paperid`)
);

CREATE TABLE `pcmember` (
  `email` varchar(50) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `assigned` int,
  PRIMARY KEY (`email`)
);

CREATE TABLE `review` (
  `reportid` INT AUTO_INCREMENT,
  `sdate` date DEFAULT NULL,
  `comment` varchar(250) DEFAULT NULL,
  `recommendation` char(1) DEFAULT NULL,
  `paperid` int(11) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  UNIQUE KEY `paperid` (`paperid`,`email`),
  KEY `email` (`email`),
  PRIMARY KEY (`reportid`),
);


