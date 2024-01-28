package com.mfy98.flightsearchapi.repo;

import com.mfy98.flightsearchapi.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MockRepository extends JpaRepository<Flight, Long> {

}