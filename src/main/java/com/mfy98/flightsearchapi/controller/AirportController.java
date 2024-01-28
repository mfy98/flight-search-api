package com.mfy98.flightsearchapi.controller;

import com.mfy98.flightsearchapi.entity.Airport;
import com.mfy98.flightsearchapi.service.AirportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {

        this.airportService = airportService;
    }

    @GetMapping
    public ResponseEntity<List<Airport>> getAllAirports() {
        List<Airport> airports = airportService.getAllAirports();
        return ResponseEntity.ok(airports);
    }

    @GetMapping("/get/{airportId}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long airportId) {
        Airport airport = airportService.getAirportById(airportId);
        return ResponseEntity.ok(airport);
    }

    @PostMapping
    public ResponseEntity<Airport> createAirport(@RequestBody Airport airport) {
        Airport createdAirport = airportService.createAirport(airport);
        return ResponseEntity.ok(createdAirport);
    }

    @PutMapping("/update/{airportId}")
    public ResponseEntity<Airport> updateAirport(@PathVariable Long airportId, @RequestBody Airport airport) {
        Airport updatedAirport = airportService.updateAirport(airportId, airport);
        return ResponseEntity.ok(updatedAirport);
    }

    @DeleteMapping("/delete/{airportId}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long airportId) {
        airportService.deleteAirport(airportId);
        return ResponseEntity.noContent().build();
    }
}