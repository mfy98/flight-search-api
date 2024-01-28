package com.mfy98.flightsearchapi.config;
import com.mfy98.flightsearchapi.entity.Airport;
import com.mfy98.flightsearchapi.entity.Flight;
import com.mfy98.flightsearchapi.repo.FlightRepository;
import com.mfy98.flightsearchapi.repo.AirportRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Configuration
public class InitDataLoader {

    @Bean
    public CommandLineRunner initializeData(
            FlightRepository flightRepository,
            AirportRepository airportRepository
    ) {
        return args -> {
            createTestData(flightRepository, airportRepository);
        };
    }

    private void createTestData(FlightRepository flightRepository, AirportRepository airportRepository) {
        List<Flight> flights = Arrays.asList(
                createFlight("EEE", "JFK", LocalDateTime.now(), LocalDateTime.now().plusHours(6), BigDecimal.valueOf(500.00)),
                createFlight("NBT", "DNB", LocalDateTime.now(), LocalDateTime.now().plusHours(12), BigDecimal.valueOf(700.00)),
                createFlight("CCV", "TRE", LocalDateTime.now(), BigDecimal.valueOf(222.00)),
                createFlight("IUR", "WER", LocalDateTime.now(), BigDecimal.valueOf(333.00)),
                createFlight("GPK", "CVB", LocalDateTime.now(), LocalDateTime.now().plusHours(2), BigDecimal.valueOf(444.00)),
                createFlight("CIU", "QNV", LocalDateTime.now(), LocalDateTime.now().plusHours(4), BigDecimal.valueOf(555.00)),
                createFlight("OYR", "OUY", LocalDateTime.now(), LocalDateTime.now().plusHours(3), BigDecimal.valueOf(573.00)),
                createFlight("MOY", "TTT", LocalDateTime.now(), BigDecimal.valueOf(856.00))
        );

        List<Airport> airports = Arrays.asList(
                createAirport("New York"),
                createAirport("Dubai"),
                createAirport("London"),
                createAirport("York"),
                createAirport("Izmir"),
                createAirport("Hatay"),
                createAirport("Urfa"),
                createAirport("Antep")

        );
        airportRepository.saveAll(airports);
        flightRepository.saveAll(flights);
    }

    private Flight createFlight(String departure, String arrival, LocalDateTime departureDateTime, LocalDateTime returnDateTime, BigDecimal price) {
        Flight flight = new Flight();
        flight.setDepartureAirport(departure);
        flight.setArrivalAirport(arrival);
        flight.setDepartureDatetime(departureDateTime);
        flight.setReturnDatetime(returnDateTime);
        flight.setPrice(price);
        System.out.println("New Flight added!");
        return flight;
    }
    private Flight createFlight(String departure, String arrival, LocalDateTime departureDateTime, BigDecimal price) {
        Flight flight = new Flight();
        flight.setDepartureAirport(departure);
        flight.setArrivalAirport(arrival);
        flight.setDepartureDatetime(departureDateTime);
        flight.setPrice(price);
        return flight;
    }

    private Airport createAirport(String city) {
        Airport airport = new Airport();
        airport.setCity(city);
        return airport;
    }
}