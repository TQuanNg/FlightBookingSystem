package com.example.flightapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Flights") // Maps to the "Flights" table in the database
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Flight {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate the primary key
    private Long flightId;

    @Column(name = "flight_number", nullable = false, length = 10) // Maps to flight_number column
    private String flightNumber;

    @Column(name = "departure_city", nullable = false, length = 50) // Maps to departure_city column
    private String departureCity;

    @Column(name = "arrival_city", nullable = false, length = 50) // Maps to arrival_city column
    private String arrivalCity;

    @Column(name = "departure_time", nullable = false) // Maps to departure_time column
    private LocalDateTime departureTime;

    @Column(name = "arrival_time", nullable = false) // Maps to arrival_time column
    private LocalDateTime arrivalTime;

    @Column(name = "price", nullable = false, precision = 10, scale = 2) // Maps to price column
    private BigDecimal price;

    @Column(name = "available_seats", nullable = false) // Maps to available_seats column
    private Integer availableSeats;
}
