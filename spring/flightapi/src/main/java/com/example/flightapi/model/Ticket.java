package com.example.flightapi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Ticket")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flightId;

    @Column(name = "ticket_date", nullable = false)
    private LocalDateTime ticketDate = LocalDateTime.now();

    @Column(name = "number_of_travelers", nullable = false)
    private Integer numberOfTravelers;

    @Column(name = "boarding_group", nullable = false, length = 3)
    private String boardingGroup;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;
    
}
