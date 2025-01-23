package com.example.flightapi.model.DTO;

import java.math.BigDecimal;

public class CartDTO {
    private Long flightId;
    private Long cartId;
    private String departurePlace;
    private String departureTime;
    private String arrivalPlace;
    private String arrivalTime;
    private BigDecimal price;
    private Integer numberOfTravelers;
    
    public CartDTO(Long flightId, String departurePlace, String departureTime, String arrivalPlace, String arrivalTime,
            BigDecimal price, Integer numberOfTravelers) {
        this.flightId = flightId;
        this.departurePlace = departurePlace;
        this.departureTime = departureTime;
        this.arrivalPlace = arrivalPlace;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.numberOfTravelers = numberOfTravelers;
    }

    public CartDTO(Long cartId, Long flightId, String departurePlace, String arrivalPlace,
            BigDecimal price, Integer numberOfTravelers) {
        this.cartId = cartId;
        this.flightId = flightId;
        this.departurePlace = departurePlace;
        this.arrivalPlace = arrivalPlace;
        this.price = price;
        this.numberOfTravelers = numberOfTravelers;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getFlightId() {
        return flightId;
    }
    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }
    public String getDeparturePlace() {
        return departurePlace;
    }
    public void setDeparturePlace(String departurePlace) {
        this.departurePlace = departurePlace;
    }
    public String getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
    public String getArrivalPlace() {
        return arrivalPlace;
    }
    public void setArrivalPlace(String arrivalPlace) {
        this.arrivalPlace = arrivalPlace;
    }
    public String getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public Integer getNumberOfTravelers() {
        return numberOfTravelers;
    }
    public void setNumberOfTravelers(Integer numberOfTravelers) {
        this.numberOfTravelers = numberOfTravelers;
    }

   

    
}
