package com.mfy98.flightsearchapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "departure_airport", nullable = false)
    private String departureAirport;

    @Column(name = "arrival_airport", nullable = false)
    private String arrivalAirport;

    @Column(name = "departure_datetime", nullable = false)
    private LocalDateTime departureDatetime;

    @Column(name = "return_datetime")
    private LocalDateTime returnDatetime;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    public Flight(String departureAirport, String arrivalAirport, LocalDateTime departureDatetime,
                  LocalDateTime returnDatetime, BigDecimal price) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDatetime = departureDatetime;
        this.returnDatetime = returnDatetime;
        this.price = price;
    }
    public Flight(String departureAirport, String arrivalAirport, LocalDateTime departureDatetime,
                  BigDecimal price) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDatetime = departureDatetime;
        this.price = price;
    }

    public Flight() {

    }
}