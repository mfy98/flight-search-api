package com.mfy98.flightsearchapi.service;

import com.mfy98.flightsearchapi.entity.Airport;
import com.mfy98.flightsearchapi.repo.AirportRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<Airport> getAllAirports() {

        return airportRepository.findAll();
    }

    public Airport getAirportById(Long airportId) {
        return airportRepository.findById(airportId)
                .orElseThrow(() -> new EntityNotFoundException("Airport not found with id: " + airportId));
    }

    public Airport createAirport(Airport airport) {

        return airportRepository.save(airport);
    }

    public Airport updateAirport(Long airportId, Airport airport) {
        Airport existingAirport = getAirportById(airportId);
        existingAirport.setCity(airport.getCity());

        return airportRepository.save(existingAirport);
    }

    public void deleteAirport(Long airportId) {
        Airport existingAirport = getAirportById(airportId);
        airportRepository.delete(existingAirport);
    }
}