package com.keyin.Passengers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {
    private final PassengerRepository passengerRepository;

    @Autowired
    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    // gte the passengers
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();

        // uses passengerRepository to integrate with the operations findAll() and etc...
    }

    // get the passengers by ID
    public Passenger getPassengerById(Long id) {
        return passengerRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found."));
    }

    // save a passenger
    public Passenger savePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    // delete a passenger
    public void deletePassenger(Long id) {
        passengerRepository.deleteById(id);
    }

    //added to updates details of an existing passenger by id
    public Passenger updatePassenger(Long id, Passenger updatedPassenger) {
        Passenger existingPassenger = passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        existingPassenger.setFirstName(updatedPassenger.getFirstName());
        existingPassenger.setLastName(updatedPassenger.getLastName());
        existingPassenger.setPhoneNumber(updatedPassenger.getPhoneNumber());

        return passengerRepository.save(existingPassenger);
    }


}
