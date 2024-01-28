package com.mfy98.flightsearchapi.service;

import com.mfy98.flightsearchapi.entity.Flight;
import com.mfy98.flightsearchapi.repo.MockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MockService {

    private final MockRepository mockRepository;

    public MockService(MockRepository mockRepository) {
        this.mockRepository = mockRepository;
    }

    public Flight createFlight(Flight flight) {
        return mockRepository.save(flight);
    }

    public void addDatabaseMockDatas(List<Flight> mockFlights) {
        mockRepository.saveAll(mockFlights);
    }

    public List<Flight> getAllFlights() {
        return mockRepository.findAll();
    }
}