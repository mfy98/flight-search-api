package com.mfy98.flightsearchapi.repo;

import com.mfy98.flightsearchapi.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByDepartureAirport(String departureAirport);

    @Query("SELECT f FROM Flight f WHERE f.departureAirport = :departure AND f.arrivalAirport = :arrival AND f.departureDatetime >= :departureDateTime")
    List<Flight> findOneWayFlights(@Param("departure") String departure,
                                   @Param("arrival") String arrival,
                                   @Param("departureDateTime") LocalDateTime departureDateTime);

    @Query("SELECT f FROM Flight f WHERE (f.departureAirport = :departure AND f.arrivalAirport = :arrival AND f.departureDatetime >= :departureDateTime) OR (f.departureAirport = :arrival AND f.arrivalAirport = :departure AND f.departureDatetime >= :returnDateTime)")
    List<Flight> findRoundTripFlights(@Param("departure") String departure,
                                      @Param("arrival") String arrival,
                                      @Param("departureDateTime") LocalDateTime departureDateTime,
                                      @Param("returnDateTime") LocalDateTime returnDateTime);


}