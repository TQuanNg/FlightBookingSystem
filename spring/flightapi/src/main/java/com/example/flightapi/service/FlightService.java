package com.example.flightapi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flightapi.model.Entity.Flight;
import com.example.flightapi.repository.FlightRepository;
import com.example.flightapi.util.AutoGenerateTicket;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AutoGenerateTicket autoGenerateTicket;

    public FlightService(FlightRepository flightRepository, AutoGenerateTicket autoGenerateTicket) {
        this.flightRepository = flightRepository;
        this.autoGenerateTicket = autoGenerateTicket;
    }

    public List<Flight> searchFlights(String departureCity,
            String arrivalCity,
            LocalDateTime startTime,
            LocalDateTime endTime,
            Integer numTravelers) {

        List<Flight> flights = flightRepository.findByUserInput(
                departureCity, arrivalCity, startTime, endTime, numTravelers);

        // If no flights found, generate random ones
        if (flights.isEmpty()) {
            List<Flight> generatedFlights = creatingRandomFlights(departureCity, arrivalCity, 5);
            return generatedFlights;
        }

        return flights;
    }

    private List<Flight> creatingRandomFlights(String departureCity, String arrivalCity, int numberOfFlight) {
        List<Flight> generatedFlights = autoGenerateTicket.generateFlights(departureCity, arrivalCity, numberOfFlight);

        // Optional: Save them to the database
        flightRepository.saveAll(generatedFlights);

        return generatedFlights;
    }
}
