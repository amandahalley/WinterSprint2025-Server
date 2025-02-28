package com.keyin.Aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class AircraftController {

    @Autowired
    private AircraftService aircraftService; //inserts AircraftService to handle logic

    @Autowired
    private AircraftRepository aircraftRepository;

    //for get all aircraft
    @GetMapping("/api/aircraft")
    public List<Aircraft> getAllAircrafts() {
        return (List<Aircraft>) aircraftRepository.findAll();
    }

    //for retrieving aircraft by ID
    @GetMapping("/api/aircraft/{id}")
    public Aircraft getAircraftById(@PathVariable long id) {
        return aircraftService.findAircraftById(id);
    }

    //for retrieving aircraft based on type/airline
    @GetMapping("/api/aircraft_search")
    public List<Aircraft> searchAircrafts(@RequestParam(value = "type", required = false) String type, @RequestParam(value = "airlineName", required = false) String airlineName){
        List<Aircraft> results = new ArrayList<>();

        if (type != null) {
            results.addAll(aircraftService.findByType(type));
        } else if (airlineName != null) {
            results.addAll(aircraftService.findByAirlineName(airlineName));
        }
        return results;
    }

    //for creating a new aircraft entry
    @PostMapping("/api/aircraft")
    public Aircraft createAircraft(@RequestBody Aircraft newAircraft) {
        return aircraftService.createAircraft(newAircraft);
    }


    //for updating an existing aircraft by ID
    @PutMapping("/api/aircraft/{id}")
    public Aircraft updateAircraft(@PathVariable long id, @RequestBody Aircraft updatedAircraft) {
        return aircraftService.updateAircraft(id, updatedAircraft);
    }
    // Assign a passenger to an aircraft
    @PostMapping("/api/{aircraftId}/passenger/{passengerId}")
    public ResponseEntity<Aircraft> addPassengerToAircraft(
            @PathVariable Long aircraftId, @PathVariable Long passengerId) {
        Aircraft updatedAircraft = aircraftService.addPassengerToAircraft(aircraftId, passengerId);
        return updatedAircraft != null ? ResponseEntity.ok(updatedAircraft) : ResponseEntity.notFound().build();
    }


}

