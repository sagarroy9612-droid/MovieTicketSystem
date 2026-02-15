CREATE DATABASE MTS;
USE MTS;

CREATE TABLE users(
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(15) NOT NULL,
    password VARCHAR(15) NOT NULL,
    role ENUM('admin','user') NOT NULL
);

INSERT INTO users (username, password, role) 
VALUES ('a','1','admin'),('u','u','user');

CREATE TABLE movies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200),
    genre VARCHAR(100),
    time VARCHAR(50),
    price DECIMAL(10,2),
    seats INT
);

CREATE TABLE bookings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    movie VARCHAR(255) NOT NULL,
    user VARCHAR(255) NOT NULL,
    seats_book INT NOT NULL
);
