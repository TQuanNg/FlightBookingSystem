package com.example.flightapi.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.flightapi.model.Entity.Flight;
import org.springframework.stereotype.Component;

@Component
public class AutoGenerateTicket {
    private final Random random = new Random();

    public List<Flight> generateFlights(String departureCity, String arrivalCity, int count) {
        List<Flight> flights = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Flight flight = new Flight();
            flight.setFlightNumber(generateFlightNumber());
            flight.setDepartureCity(departureCity);
            flight.setArrivalCity(arrivalCity);

            LocalDateTime departureTime = generateRandomFutureTime();
            LocalDateTime arrivalTime = departureTime.plusHours(1 + random.nextInt(10)); // 1-10 hours flight
            flight.setDepartureTime(departureTime);
            flight.setArrivalTime(arrivalTime);

            flight.setPrice(generateRandomPrice());
            flight.setAvailableSeats(50 + random.nextInt(151)); // 50–200 seats

            flights.add(flight);
        }

        return flights;
    }

    private String generateFlightNumber() {
        return "FL" + (1000 + random.nextInt(9000)); // FL1000 - FL9999
    }

    private LocalDateTime generateRandomFutureTime() {
        return LocalDateTime.now().plusDays(random.nextInt(30)).plusHours(random.nextInt(24));
    }

    private BigDecimal generateRandomPrice() {
        double price = 100 + (5000 - 100) * random.nextDouble(); // $100 - $5000
        return BigDecimal.valueOf(price).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
