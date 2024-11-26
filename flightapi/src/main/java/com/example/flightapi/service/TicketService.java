package com.example.flightapi.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flightapi.model.Flight;
import com.example.flightapi.model.PurchaseHistory;
import com.example.flightapi.model.Ticket;
import com.example.flightapi.model.User;
import com.example.flightapi.repository.FlightRepository;
import com.example.flightapi.repository.PurchaseRepository;
import com.example.flightapi.repository.TicketRepository;
import com.example.flightapi.repository.UserRepository;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final PurchaseRepository purchaseRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, UserRepository userRepository, FlightRepository flightRepository, PurchaseRepository purchaseRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.purchaseRepository = purchaseRepository;
    }

    /*
     * The process of ticket booking involve:
     * userId(based on Login), flightId(based on chosen flight)
     * 
     * Create a ticket object, set its fields, and save it for front end to load
     * 
     * Save it to PurchaseHistory
     * 
     * This class should be similar to following SQL commands
     * SELECT flight_id, departure_city, arrival_city, departure_tume, arrival_time
     * FROM Flights
     * 
     * SELECT ticket_id, total_price, ticket_date, number_of_tralevers, boarding_group
     * FROM Ticket
     */
     
    public BookingSummaryDTO bookTicket(Long userId, Long flightId, Integer numberOfTravelers, String boardingGroup) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Flight> flightOptional = flightRepository.findById(flightId);
        Flight flight = flightOptional.orElseThrow(() -> new RuntimeException("Flight not found"));

        BigDecimal totalPrice = calculateTotal(flight, numberOfTravelers);

        Ticket ticket = new Ticket();
        ticket.setUserId(user.get());
        ticket.setFlightId(flight);
        ticket.setNumberOfTravelers(numberOfTravelers);
        ticket.setBoardingGroup(boardingGroup);
        ticket.setTotalPrice(totalPrice);

        Ticket savedTicket = ticketRepository.save(ticket);

        // Add the ticket to Purchase History
        PurchaseHistory purchaseHistory = new PurchaseHistory();
        purchaseHistory.setUser(user.get());
        purchaseHistory.setTicket(savedTicket);
        purchaseRepository.save(purchaseHistory);

        // Prepare the DTO with all details
        BookingSummaryDTO summary = new BookingSummaryDTO();
        summary.setTicketId(ticket.getTicketId());
        summary.setDeparturePlace(flight.getDepartureCity());
        summary.setDepartureTime(flight.getDepartureTime().toString());
        summary.setArrivalPlace(flight.getArrivalCity());
        summary.setArrivalTime(flight.getArrivalTime().toString());
        summary.setPrice(totalPrice);
        summary.setPurchaseDate(ticket.getTicketDate());
        summary.setNumberOfTravelers(numberOfTravelers);
        summary.setBoardingGroup(boardingGroup);

        return summary;
    }

    public BigDecimal calculateTotal(Flight flight, Integer numberOfTravelers) {
        return flight.getPrice().multiply(BigDecimal.valueOf(numberOfTravelers));
    }

    public List<PurchaseHistory> getPurchaseHistory(Long userId) {
        return purchaseRepository.findByUserId(userId);
    }
}
