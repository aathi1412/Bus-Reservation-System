CREATE DATABASE IF NOT EXISTS bus_reservation;

USE bus_reservation;

CREATE TABLE users(
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    phone VARCHAR(15) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(10) NOT NULL
);

CREATE TABLE buses(
    bus_id INT AUTO_INCREMENT PRIMARY KEY,
    bus_name VARCHAR(50) NOT NULL,
    source VARCHAR(50) NOT NULL,
    destination VARCHAR(50) NOT NULL,
    total_seats INT NOT NULL,
    available_seats INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    bus_type VARCHAR(10) NOT NULL
);

CREATE TABLE bookings(
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    bus_id INT NOT NULL,
    seats_booked INT NOT NULL,
    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (bus_id) REFERENCES buses (bus_id)
);

INSERT INTO users(name, email, phone, password, role)
VALUES(
    'Admin',
    'admin@gmail.com',
    '1234567890',
    'Admin@123',
    'ADMIN'
);