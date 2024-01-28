package com.mfy98.flightsearchapi.controller;

import com.mfy98.flightsearchapi.entity.Flight;
import com.mfy98.flightsearchapi.service.FlightService;
import com.mfy98.flightsearchapi.service.MockService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/mock")
public class MockController {

    private final FlightService flightService;
    private final MockService mockService;

    public MockController(FlightService flightService, MockService mockService) {
        this.flightService = flightService;
        this.mockService = mockService;
    }

    private final List<Flight> mockFlights = new ArrayList<>();

    private List<Flight> generateMockFlights() {
        List<Flight> flights = new ArrayList<>();

        flights.add(new Flight("WRT", "AZK", LocalDateTime.now(), LocalDateTime.now().plusDays(1), BigDecimal.valueOf(500)));
        flights.add(new Flight("LHR", "SFO",LocalDateTime.now(), BigDecimal.valueOf(600)));
        flights.add(new Flight("DXB", "ORD",LocalDateTime.now(), LocalDateTime.now().plusDays(3), BigDecimal.valueOf(700)));
        flights.add(new Flight("DAS", "ORD",LocalDateTime.now(), LocalDateTime.now().plusDays(3), BigDecimal.valueOf(800)));
        flights.add(new Flight("CXB", "AAA",LocalDateTime.now(), BigDecimal.valueOf(900)));
        flights.add(new Flight("VVB", "QQQ",LocalDateTime.now(), LocalDateTime.now().plusDays(3), BigDecimal.valueOf(100)));
        flights.add(new Flight("DTT", "WWW",LocalDateTime.now(), BigDecimal.valueOf(400)));
        flights.add(new Flight("DCC", "RRR",LocalDateTime.now(), BigDecimal.valueOf(200)));
        flights.add(new Flight("DZC", "OTT",LocalDateTime.now(), LocalDateTime.now().plusDays(3), BigDecimal.valueOf(300)));

        return flights;
    }
    @Scheduled(cron = "0 0 * * *")
    @GetMapping("/updateFlights")
    public void updateMockFlights() {
        mockFlights.addAll(generateMockFlights());
        mockService.addDatabaseMockDatas(mockFlights);
        System.out.println(mockFlights);
    }

    @GetMapping("/flights")
    public ResponseEntity<List<Flight>> getMockFlights() {
        List<Flight> flights = mockService.getAllFlights();
        return ResponseEntity.ok(flights);
    }
}