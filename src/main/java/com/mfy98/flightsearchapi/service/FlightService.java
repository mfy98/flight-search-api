package com.mfy98.flightsearchapi.service;

import com.mfy98.flightsearchapi.entity.Flight;
import com.mfy98.flightsearchapi.repo.FlightRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {

        this.flightRepository = flightRepository;
    }

    public List<Flight> getAllFlights() {

        return flightRepository.findAll();
    }

    public Flight getFlightById(Long flightId) {
        return flightRepository.findById(flightId)
                .orElseThrow(() -> new EntityNotFoundException("Flight not found with id: " + flightId));
    }

    public Flight createFlight(Flight flight) {

        return flightRepository.save(flight);
    }

    public Flight updateFlight(Long flightId, Flight flight) {
        Flight existingFlight = getFlightById(flightId);
        existingFlight.setDepartureAirport(flight.getDepartureAirport());
        existingFlight.setArrivalAirport(flight.getArrivalAirport());
        existingFlight.setDepartureDatetime(flight.getDepartureDatetime());
        existingFlight.setReturnDatetime(flight.getReturnDatetime());
        existingFlight.setPrice(flight.getPrice());
        return flightRepository.save(existingFlight);
    }

    public void deleteFlight(Long flightId) {
        Flight existingFlight = getFlightById(flightId);
        flightRepository.delete(existingFlight);
    }

    public List<Map<String, Object>> getFlightInfoList(List<Flight> flights, LocalDateTime returnDateTime) {
        Map<String, Object> flightTypeInfo = new HashMap<>();
        flightTypeInfo.put("type", returnDateTime == null ? "One Way" : "Round Trip");

        List<Map<String, Object>> flightsWithTypeInfo = new ArrayList<>();
        for (Flight flight : flights) {
            Map<String, Object> flightInfo = new HashMap<>();
            flightInfo.put("flight", flight);
            flightInfo.put("typeInfo", flightTypeInfo);
            flightsWithTypeInfo.add(flightInfo);
        }

        return flightsWithTypeInfo;
    }
    public List<Flight> searchFlights(String departure, String arrival, LocalDateTime departureDateTime, LocalDateTime returnDateTime) {
        return returnDateTime == null
                ? flightRepository.findOneWayFlights(departure, arrival, departureDateTime)
                : flightRepository.findRoundTripFlights(departure, arrival, departureDateTime, returnDateTime);

    }
}