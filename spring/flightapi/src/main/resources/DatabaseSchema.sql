CREATE TABLE Users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Flights (
    flight_id SERIAL PRIMARY KEY,
    flight_number VARCHAR(10) NOT NULL,
    departure_city VARCHAR(50) NOT NULL,
    arrival_city VARCHAR(50) NOT NULL,
    departure_time TIMESTAMP NOT NULL,
    arrival_time TIMESTAMP NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    available_seats INT NOT NULL
);

CREATE TABLE FlownBy (
	flight_id int
	airplane_id int 
	PRIMARY KEY (flight_id, airplane_id)
	FOREIGN KEY (flight_id) REFERENCES flights(flight_id) ON DELETE CASCADE
	FOREIGN KEY (airplane_id) REFERENCES airplane(airplane_id) ON DELETE CASCADE
);

CREATE TABLE Ticket (
    ticket_id SERIAL,
    user_id INT 
    flight_id INT
    ticket_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    number_of_travelers INT NOT NULL,
    boarding_group CHAR(3) NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL
    PRIMARY KEY(ticket_number, flight_id)
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
    FOREIGN KEY (flights_id) REFERENCES flights(flight_id) ON DELETE CASCADE
);

CREATE TABLE PurchaseHistory (
    purchase_id SERIAL,
    user_id INT,
    ticket_id INT,
    purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    PRIMARY KEY(user_id, ticket_id)
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
    FOREIGN KEY (ticket_id) REFERENCES ticket(ticket_id) ON DELETE CASCADE
);

CREATE TABLE CartItem (
    cart_item_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    flight_id INT NOT NULL,
    number_of_travelers INT NOT NULL,
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (flight_id) REFERENCES Flights(flight_id) ON DELETE CASCADE
);