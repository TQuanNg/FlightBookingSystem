package com.example.flightapi.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingSummaryDTO {
    private Long ticketId;
    private String departurePlace;
    private String departureTime;
    private String arrivalPlace;
    private String arrivalTime;
    private BigDecimal price;
    private LocalDateTime purchaseDate;
    private Integer numberOfTravelers;
    private String boardingGroup;
}
