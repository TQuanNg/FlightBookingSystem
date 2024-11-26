package com.example.flightapi.repository;
import com.example.flightapi.model.Flight; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    /* 
    List<Flight> findByUserInput(
     String departureCity,
     String arrivalCity,
     LocalDateTime startTime,
     LocalDateTime endTime,
     Integer numTravelers);*/

     
      @Query(value = "SELECT * FROM flights " +
                   "WHERE departure_city = :departureCity " +
                   "AND arrival_city = :arrivalCity " +
                   "AND departure_time >= :startTime " +
                   "AND departure_time <= :endTime " +
                   "AND available_seats >= :numTravelers", 
           nativeQuery = true)
    List<Flight> findByUserInput(
        @Param("departureCity") String departureCity,
        @Param("arrivalCity") String arrivalCity,
        @Param("startTime") LocalDateTime startTime,
        @Param("endTime") LocalDateTime endTime,
        @Param("numTravelers") Integer numTravelers
    );
      
}
