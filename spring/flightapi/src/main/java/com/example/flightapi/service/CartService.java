package com.example.flightapi.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.flightapi.model.DTO.CartDTO;
import com.example.flightapi.model.Entity.CartItem;
import com.example.flightapi.model.Entity.Flight;
import com.example.flightapi.repository.CartRepository;
import com.example.flightapi.repository.FlightRepository;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private FlightRepository flightRepository;

    public void addToCart(Long userId, Long flightId, Integer numberOfTravelers) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new IllegalArgumentException("Flight not found"));

        BigDecimal totalPrice = calculateTotal(flight, numberOfTravelers);

        CartItem cartItem = new CartItem();
        cartItem.setUserId(userId);
        cartItem.setFlightId(flightId);
        cartItem.setQuantity(numberOfTravelers);
        cartItem.setAddedAt(LocalDateTime.now());
        cartItem.setTotalPrice(totalPrice);
        

        cartRepository.save(cartItem);
    }

    public BigDecimal calculateTotal(Flight flight, Integer numberOfTravelers) {
        return flight.getPrice().multiply(BigDecimal.valueOf(numberOfTravelers));
    }

    public List<CartDTO> getCartItems(Long userId) {
        List<Object[]> cartItems = cartRepository.findCartItemsByUserId(userId);
        
       return cartItems.stream()
        .map(result -> new CartDTO(
            (Long) result[0], // cartId
            (Long) result[1], // flightId
            (String) result[2], // departurePlace
            (String) result[3], // arrivalPlace
            (BigDecimal) result[4], // price
            (Integer) result[5] // numberOfTravelers
        ))
        .toList();
    }

    @Transactional
    public void clearCart(Long userId, Long cartItemId) {
        if (cartItemId != null) {
            cartRepository.deleteByUserIdAndCartItemId(userId, cartItemId);
        } else {
            cartRepository.deleteByUserId(userId);
        }
    }
}
