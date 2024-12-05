package com.example.flightapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.flightapi.service.BookingSummaryDTO;
import com.example.flightapi.service.PurchaseHistoryDTO;
import com.example.flightapi.service.TicketService;

@RestController
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/book")
    public ResponseEntity<BookingSummaryDTO> bookTicket(
            @RequestParam Long userId,
            @RequestParam Long flightId,
            @RequestParam Integer numberOfTravelers,
            @RequestParam String boardingGroup) {

        BookingSummaryDTO summary = ticketService.bookTicket(userId, flightId, numberOfTravelers, boardingGroup);
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/history")
    public ResponseEntity<List<PurchaseHistoryDTO>> getPurchaseHistory(@RequestParam Long userId) {
        List<PurchaseHistoryDTO> history = ticketService.getPurchaseHistory(userId);
        return ResponseEntity.ok(history);
    }
}
