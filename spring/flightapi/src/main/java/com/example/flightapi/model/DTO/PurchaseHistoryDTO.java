package com.example.flightapi.model.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PurchaseHistoryDTO {
    private LocalDateTime purchaseDate;
    private LocalDateTime depart;
    private String origin;
    private String destination;
    private LocalDateTime arrival;
    private String flightNumber;
    private BigDecimal price;

    public PurchaseHistoryDTO(LocalDateTime purchaseDate, LocalDateTime depart, String origin, String destination,
            LocalDateTime arrival, String flightNumber, BigDecimal price) {
        this.purchaseDate = purchaseDate;
        this.depart = depart;
        this.origin = origin;
        this.destination = destination;
        this.arrival = arrival;
        this.flightNumber = flightNumber;
        this.price = price;
    }

    public LocalDateTime getDate() {
        return purchaseDate;
    }

    public void setDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDateTime getDepart() {
        return depart;
    }

    public void setDepart(LocalDateTime depart) {
        this.depart = depart;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
