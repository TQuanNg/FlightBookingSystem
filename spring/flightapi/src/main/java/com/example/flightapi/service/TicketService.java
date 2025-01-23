package com.example.flightapi.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flightapi.model.DTO.BookingSummaryDTO;
import com.example.flightapi.model.DTO.PurchaseHistoryDTO;
import com.example.flightapi.model.Entity.Flight;
import com.example.flightapi.model.Entity.PurchaseHistory;
import com.example.flightapi.model.Entity.Ticket;
import com.example.flightapi.model.Entity.User;
import com.example.flightapi.repository.CartRepository;
import com.example.flightapi.repository.FlightRepository;
import com.example.flightapi.repository.PurchaseRepository;
import com.example.flightapi.repository.TicketRepository;
import com.example.flightapi.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final PurchaseRepository purchaseRepository;
    private final CartRepository cartRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, UserRepository userRepository,
            FlightRepository flightRepository, PurchaseRepository purchaseRepository, CartRepository cartRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.purchaseRepository = purchaseRepository;
        this.cartRepository = cartRepository;
    }

    @Transactional
    public void deleteCartItem(Long userId, Long cartId) {
        cartRepository.deleteByUserIdAndCartItemId(userId, cartId);
    }

    /*
     * The process of ticket booking involve:
     * userId(based on Login), flightId(based on chosen flight)
     * 
     * Create a ticket object, set its fields, and save it for front end to load
     * 
     * Save it to PurchaseHistory
     * 
     * Since this method change database, transactional is required
     * 
     * This class should be similar to following SQL commands
     * SELECT flight_id, departure_city, arrival_city, departure_tume, arrival_time
     * FROM Flights
     * 
     * SELECT ticket_id, total_price, ticket_date, number_of_tralevers,
     * boarding_group
     * FROM Ticket
     */
    @Transactional
    public BookingSummaryDTO bookTicket(Long userId, Long flightId, Long cartId, Integer numberOfTravelers, String boardingGroup) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Flight> flightOptional = flightRepository.findById(flightId);
        Flight flight = flightOptional.orElseThrow(() -> new RuntimeException("Flight not found"));

        BigDecimal totalPrice = calculateTotal(flight, numberOfTravelers);

        Ticket ticket = createTicket(user, flight, numberOfTravelers, boardingGroup, totalPrice);
        Ticket savedTicket = ticketRepository.save(ticket);

        // Add the ticket to Purchase History
        PurchaseHistory purchaseHistory = addToPurchaseHistory(user, savedTicket);
        purchaseRepository.save(purchaseHistory);

        updateAvailableSeats(flightId, numberOfTravelers);
        
        deleteCartItem(userId, cartId);

        return prepareBookingSummary(savedTicket, flight, totalPrice, numberOfTravelers, boardingGroup);
    }

    private Ticket createTicket(Optional<User> user, Flight flight, Integer numberOfTravelers, String boardingGroup, BigDecimal totalPrice) {
        Ticket ticket = new Ticket();
        ticket.setUserId(user.get());
        ticket.setFlightId(flight);
        ticket.setNumberOfTravelers(numberOfTravelers);
        ticket.setBoardingGroup(boardingGroup);
        ticket.setTotalPrice(totalPrice);
        return ticket;
    }

    private PurchaseHistory addToPurchaseHistory(Optional<User> user, Ticket savedTicket) {
        PurchaseHistory purchaseHistory = new PurchaseHistory();
        purchaseHistory.setUser(user.get());
        purchaseHistory.setTicket(savedTicket);
        return purchaseHistory;
    }

    private void updateAvailableSeats(Long flightId, Integer numberOfTravelers) {
        int updatedSeats = flightRepository.updateAvailableSeats(flightId, numberOfTravelers);
        if (updatedSeats == 0) {
            throw new RuntimeException("Failed to update available seats. Flight may be overbooked.");
        }
    }

    private BookingSummaryDTO prepareBookingSummary(Ticket ticket, Flight flight, BigDecimal totalPrice, Integer numberOfTravelers, String boardingGroup) {
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

    public List<PurchaseHistoryDTO> getPurchaseHistory(Long userId) {
        List<Object[]> rawResults = purchaseRepository.findByUserId(userId);

        // Manually map the raw results to DTOs
        List<PurchaseHistoryDTO> purchaseHistoryList = rawResults.stream()
                .map(result -> new PurchaseHistoryDTO(
                        convertToLocalDateTime(result[0]), // purchaseDate
                        convertToLocalDateTime(result[1]), // depart
                        (String) result[2], // origin
                        (String) result[3], // destination
                        convertToLocalDateTime(result[4]), // arrival
                        (String) result[5], // flightNumber
                        (BigDecimal) result[6] // price
                ))
                .toList();

        return purchaseHistoryList;
    }

    private LocalDateTime convertToLocalDateTime(Object timestamp) {
        if (timestamp instanceof Timestamp) {
            return ((Timestamp) timestamp).toLocalDateTime();
        }
        return null;
    }
}
