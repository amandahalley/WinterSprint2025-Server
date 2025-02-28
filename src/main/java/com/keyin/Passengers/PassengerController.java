package com.keyin.Passengers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.keyin.Airports.Airport;
import com.keyin.Airports.AirportService;
import com.keyin.Aircraft.Aircraft;

import java.util.Set;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin //added this, allows other websites (with diff domains/ports) to access resources in this controller.
public class PassengerController {

    @Autowired
    private PassengerService passengerService;
    
    @Autowired
    private AirportService airportService;
    
    // Get all passengers
    @GetMapping("/api/passengers")
    public List<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    // Get a passenger by ID
    @GetMapping("/api/passengers/{id}")
    public Passenger getPassengerById(@PathVariable Long id) {
        return passengerService.getPassengerById(id);
    }

    // Create a new passenger
    @PostMapping("/api/passenger")
    public Passenger createPassenger(@RequestBody Passenger passenger) {
        return passengerService.savePassenger(passenger);
    }

    //added in to update passenger by id
    @PutMapping("/api/passenger/{id}")
    public Passenger updatePassenger(@PathVariable Long id, @RequestBody Passenger updatedPassenger) {
        return passengerService.updatePassenger(id, updatedPassenger);
    }

    // Delete a passenger
    @DeleteMapping("/api/passenger/{id}")
    public void deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
    }
    @GetMapping("/api/{id}/aircraft")
    public ResponseEntity<Set<Aircraft>> getAircraftByPassenger(@PathVariable Long id) {
    Set<Aircraft> aircraft = passengerService.getAircraftByPassenger(id);
    return ResponseEntity.ok(aircraft);
}
    @PostMapping("/api/airports/{airportId}/passengers")
    public ResponseEntity<Passenger> addPassengerToAirport(
            @PathVariable Long airportId, @RequestBody Passenger passenger) {
        
        Optional<Airport> airportOpt = airportService.findAirportById(airportId);
        if (airportOpt.isEmpty()) {
            return ResponseEntity.notFound().build();  // If airport doesn't exist
        }

        passenger.setAirport(airportOpt.get());  // Assign airport to passenger
        Passenger newPassenger = passengerService.createPassenger(passenger);
        return ResponseEntity.ok(newPassenger);
    }
}