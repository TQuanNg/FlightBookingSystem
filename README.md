# ✈️ Flight Booking System

A full-stack flight booking system providing a seamless and secure booking experience for users. Built with React, Spring Boot, and PostgreSQL.

## 🛠️ Tech Stack
- **Frontend:** React, HTML, CSS
- **Backend:** Spring Boot, Java
- **Database:** PostgreSQL
- **Authentication:** JWT (JSON Web Token)

## 🏗️ Project Architecture
The backend follows a clean and scalable layered architecture:
- **Model:** Defines the database entities.
- **Controller:** Handles HTTP requests and responses.
- **Service:** Contains business logic.
- **Repository:** Communicates with the database via Hibernate ORM.

## 🌟 Features
- 🛫 **Flight Search & Booking:** Search for available flights and book seats.
- 🔐 **Secure Authentication:** JWT-based user login and session management.
- 🧑‍💼 **User Management:** Register, login, and manage user profiles.
- 📄 **Booking History:** View and manage past flight bookings.

### 1️⃣ Clone the Repository

git clone https://github.com/YourUsername/FlightBookingSystem.git
cd FlightBookingSystem

2️⃣ Backend Setup (Spring Boot)
Navigate to the backend folder and run:

cd backend
./mvnw spring-boot:run
The backend server runs on http://localhost:8080.

3️⃣ Database Setup (PostgreSQL)
Make sure PostgreSQL is running and create the required database

CREATE DATABASE flight_booking;
Update database configurations in application.properties.

4️⃣ Frontend Setup (React)
Navigate to the frontend folder and run:

cd frontend
npm install
npm start
The frontend app runs on http://localhost:3000.
