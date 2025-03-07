package com.keyin.Cities;

import com.keyin.Airports.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.keyin.Airports.Airport;  // Add this import


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cities") // Base URL for all city endpoints
@CrossOrigin
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private AirportService airportService;

    // Get all cities
   @GetMapping
    public ResponseEntity<List<City>> getAllCities() {
    List<City> cities = cityService.getAllCities();
    System.out.println("Cities Retrieved: " + cities);  // Debugging Line
    return ResponseEntity.ok(cities);
}

    // Get city by ID
    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Long id) {
        Optional<City> city = cityService.findCityById(id);
        return city.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Search city by name, state, or population
    @GetMapping("/search")
    public ResponseEntity<List<City>> searchCity(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "population", required = false) Integer population) {

        List<City> results = cityService.searchCities(name, state, population);
        if (results.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(results);
    }

    // Create a new city
    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody City city) {
        City createdCity = cityService.createCity(city);
        return ResponseEntity.ok(createdCity);
    }

    // Update an existing city
    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable Long id, @RequestBody City updatedCity) {
        Optional<City> cityOptional = cityService.findCityById(id);
        if (cityOptional.isPresent()) {
            City updated = cityService.updateCity(id, updatedCity);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a city
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        boolean deleted = cityService.deleteCity(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/api/cities")
    public ResponseEntity<City> createCityWithAirport(@RequestBody City city, @RequestBody Airport airport) {
        City savedCity = cityService.createCity(city);
        airport.setCity(savedCity);
        airportService.createAirport(airport);

        return ResponseEntity.ok(savedCity);
    }
    // Get all airports in a city
    @GetMapping("/{cityId}/airports")
    public ResponseEntity<List<Airport>> getAirportsByCity(@PathVariable Long cityId) {
        List<Airport> airports = cityService.getAirportsByCity(cityId);
        return ResponseEntity.ok(airports);
    }
}
