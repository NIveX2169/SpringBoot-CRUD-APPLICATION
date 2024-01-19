
---- Drop user first if they exist
--DROP USER IF EXISTS 'springstudent'@localhost;

---- Now create user with proper privileges
--CREATE USER IF NOT EXISTS 'springstudent'@localhost IDENTIFIED BY 'springstudent';
--CREATE DATABASE IF NOT EXISTS student_tracker;

--GRANT ALL PRIVILEGES ON student_tracker.* TO 'springstudent'@'localhost';
--FLUSH PRIVILEGES;


CREATE SCHEMA IF NOT EXISTS student_tracker;
USE student_tracker;

-- Table structure for table `student`
DROP TABLE IF EXISTS student;
CREATE TABLE student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(256),
    last_name VARCHAR(256),
    email VARCHAR(256)
);


