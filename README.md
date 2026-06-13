# Bus Reservation System

A console-based Bus Reservation System built using Java, JDBC, and MySQL. This project allows users to search buses, book tickets, view bookings, and cancel reservations. It also includes an admin panel for managing buses and viewing bookings.

## Features

### User

* Register a new account
* Login with email and password
* Search buses by source and destination
* View all available buses
* Book tickets
* View booked tickets
* Cancel bookings
* Logout

### Admin

* Add new buses
* View all buses
* Update bus details
* Delete buses
* View all bookings
* Logout

## Technologies Used

* Java
* JDBC
* MySQL
* OOP Concepts
* Exception Handling

## Database Tables

### users

Stores user account information.

Fields:

* user_id
* name
* email
* phone
* password
* role

### buses

Stores bus details.

Fields:

* bus_id
* bus_name
* source
* destination
* total_seats
* available_seats
* price
* bus_type

### bookings

Stores booking information.

Fields:

* booking_id
* user_id
* bus_id
* seats_booked
* total_amount
* booking_date
* status

## Project Structure

src/
├── com.bus.main
├── com.bus.model
├── com.bus.dao
├── com.bus.service
├── com.bus.util
└── com.bus.exception

## How to Run

1. Clone the repository.
2. Create the MySQL database.
3. Run the SQL scripts to create tables.
4. Update database credentials such as url, userName, password in the configuration file like db.properties.example.
5. Compile and Run the project using build.bat file.

## What I Learned

* Working with JDBC and MySQL
* Writing SQL queries in Java applications
* Layered architecture using DAO and Service classes
* Handling custom exceptions
* Managing transactions and database updates
* Building a complete CRUD-based console application

## Future Improvements

* Password hashing
* Search buses by date
* Online payment simulation
* Ticket download feature
* Spring Boot REST API version

## Author

Aathithyan
