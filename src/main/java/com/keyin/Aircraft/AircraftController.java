package com.keyin.Aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class AircraftController {

    @Autowired
    private AircraftService aircraftService; //inserts AircraftService to handle logic

    //for get all aircraft
    @GetMapping("/aircrafts")
    public List<Aircraft> getAllAircrafts() {
        return aircraftService.findAllAircrafts();
    }

    //for retrieving aircraft by ID
    @GetMapping("/aircraft/id")
    public Aircraft getAircraftById(@PathVariable long id) {
        return aircraftService.findAircraftById(id);
    }

    //for retrieving aircraft based on type/airline
    @GetMapping("/aircraft_search")
    public List<Aircraft> searchAircrafts(@RequestParam(value = "type", required = false) String type, @RequestParam(value = "airlineName", required = false) String airlineName{
        List<Aircraft> results = new ArrayList<>();

        if (type != null) {
            results.addAll(aircraftService.findByType(type));
        } else if (airlineName != null) {
            results.addAll(aircraftService.findByAirlineName(airlineName));
        }
        return results;
    }

    //for creating a new aircraft entry
    @PostMapping("/aircraft")
    public Aircraft createAircraft(@RequestBody Aircraft newAircraft) {
        return aircraftService.createAircraft(newAircraft);
    }


    //for updating an existing aircraft by ID
    @PutMapping("/aircraft/{id}")
    public Aircraft updateAircraft(@PathVariable long id, @RequestBody Aircraft updatedAircraft) {
        return aircraftService.updateAircraft(id, updatedAircraft);
    }
}
