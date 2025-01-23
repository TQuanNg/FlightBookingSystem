package com.example.flightapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.flightapi.model.DTO.BookingSummaryDTO;
import com.example.flightapi.model.DTO.CartDTO;
import com.example.flightapi.model.DTO.PurchaseHistoryDTO;
import com.example.flightapi.service.CartService;
import com.example.flightapi.service.TicketService;

@RestController
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @Autowired
    private CartService cartService;
    
    
    @PostMapping("/cart")
    public ResponseEntity<?> addToCart(
            @RequestParam Long userId,
            @RequestParam Long flightId,
            @RequestParam Integer numberOfTravelers) {

        cartService.addToCart(userId, flightId, numberOfTravelers);
        return ResponseEntity.ok("Flight added to cart successfully.");
    }
    
    @GetMapping("/cart")
    public ResponseEntity<List<CartDTO>> getCartItems(@RequestParam Long userId) {
        List<CartDTO> cartItems = cartService.getCartItems(userId);
        return ResponseEntity.ok(cartItems);
    }

    @DeleteMapping("/cart")
    public ResponseEntity<String> clearCart(@RequestParam Long userId, @RequestParam Long cartItemId) {
        cartService.clearCart(userId, cartItemId);
        return ResponseEntity.ok("Cart cleared successfully.");
    }

    


    @PostMapping("/book")
    public ResponseEntity<BookingSummaryDTO> bookTicket(
            @RequestParam Long userId,
            @RequestParam Long flightId,
            @RequestParam Long cartId,
            @RequestParam Integer numberOfTravelers,
            @RequestParam String boardingGroup) {

        BookingSummaryDTO summary = ticketService.bookTicket(userId, flightId, cartId, numberOfTravelers, boardingGroup);
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/history")
    public ResponseEntity<List<PurchaseHistoryDTO>> getPurchaseHistory(@RequestParam Long userId) {
        List<PurchaseHistoryDTO> history = ticketService.getPurchaseHistory(userId);
        return ResponseEntity.ok(history);
    }
}
