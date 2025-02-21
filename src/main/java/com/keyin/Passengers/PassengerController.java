
package com.keyin.Passengers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin //added this, allows other websites (with diff domains/ports) to access resources in this controller.
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    // Get all passengers
    @GetMapping("/passengers")
    public List<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    // Get a passenger by ID
    @GetMapping("/passenger/{id}")
    public Passenger getPassengerById(@PathVariable Long id) {
        return passengerService.getPassengerById(id);
    }

    // Create a new passenger
    @PostMapping("/passenger")
    public Passenger createPassenger(@RequestBody Passenger passenger) {
        return passengerService.savePassenger(passenger);
    }

    //added in to update passenger by id
    @PutMapping("/passenger/{id}")
    public Passenger updatePassenger(@PathVariable Long id, @RequestBody Passenger updatedPassenger) {
        return passengerService.updatePassenger(id, updatedPassenger);
    }

    // Delete a passenger
    @DeleteMapping("/passenger/{id}")
    public void deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
    }
}







//package com.keyin.Passengers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/passenger") // set up the URL.
//public class PassengerController {
//
//    private final PassengerService passengerService;
//
//    @Autowired
//    public PassengerController(PassengerService passengerService) {
//        this.passengerService = passengerService;
//    }
//
//    // Get all passengers
//    @GetMapping
//    public List<Passenger> getAllPassengers() {
//        return passengerService.getAllPassengers();
//    }
//
//    // Get a passenger by ID
//    @GetMapping("/{id}")
//    public Passenger getPassengerById(@PathVariable Long id) {
//        Passenger passenger = passengerService.getPassengerById(id);
//        return passenger;
//    }
//
//    // Creates saves a new passenger
//    @PostMapping
//    public Passenger savePassenger(@RequestBody Passenger passenger) {
//        return passengerService.savePassenger(passenger);
//    }
//
//    // Delete a passenger
//    @DeleteMapping("/{id}")
//    public void deletePassenger(@PathVariable Long id) {
//        passengerService.deletePassenger(id);
//    }
//}
