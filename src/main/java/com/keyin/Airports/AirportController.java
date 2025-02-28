package com.keyin.Airports;

import com.keyin.Cities.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")

public class AirportController {
    @Autowired
    private AirportService airportService;

    @GetMapping("/airports")
    public List<Airport> getAllAirports() {
        return airportService.findAllAirports();
    }

    @GetMapping("/airport/{id}")
    public Airport getAirportById(@PathVariable long id) {
        return airportService.findAirportById(id);
    }

    @GetMapping("/airport_search")
    public List<Airport> searchAirports(@RequestParam(value = "name", required = false) String name) {
        List<Airport> results = new ArrayList<Airport>();

        Airport airport = airportService.findByName(name);
        if (airport != null) {
            results.add(airport);
        }

        return results;
    }


    @PostMapping("/airports")
    public ResponseEntity<Airport> createAirport(@RequestBody Airport airport) {
        // Ensure that the airport has a valid city associated before saving
        if (airport.getCity() == null || airport.getCity().getId() == null) {
            return ResponseEntity.badRequest().body(null); // City is required
        }
        City city = airport.getCity(); // Get the city object
        if (city.getId() == null) {
            return ResponseEntity.badRequest().body(null); // Invalid city ID
        }
        return ResponseEntity.ok(airportService.createAirport(airport));
    }


    @PutMapping("/airport/{id}")
    public Airport updateAirport(@PathVariable long id, @RequestBody Airport updatedAirport) {
        return airportService.updateAirport(id, updatedAirport);


    }


}