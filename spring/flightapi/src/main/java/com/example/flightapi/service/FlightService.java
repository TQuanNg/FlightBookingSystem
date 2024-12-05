package com.example.flightapi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flightapi.repository.FlightRepository;
import com.example.flightapi.model.Flight;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> searchFlights(String departureCity,
            String arrivalCity,
            LocalDateTime startTime,
            LocalDateTime endTime,
            Integer numTravelers) {
        return flightRepository.findByUserInput(
                departureCity, arrivalCity, startTime, endTime, numTravelers);
    }
}
