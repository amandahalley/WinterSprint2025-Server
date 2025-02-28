package com.keyin.Aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keyin.Passengers.Passenger;
import com.keyin.Passengers.PassengerRepository;
import java.util.List;
import java.util.Optional;

@Service
public class AircraftService {

    @Autowired
    private AircraftRepository aircraftRepository;

    @Autowired
    private PassengerRepository passengerRepository;
    //for retrieving all aircraft from the database.
    public List<Aircraft> findAllAircrafts() {
        return (List<Aircraft>) aircraftRepository.findAll();
    }

    //for finding an aircraft by its ID.
    public Aircraft findAircraftById(long id) {
        Optional<Aircraft> optionalAircraft = aircraftRepository.findById(id);
        return optionalAircraft.orElse(null); // Returns the aircraft if found, otherwise returns null.
    }

    //for finding aircraft by its type
    public List<Aircraft> findByType(String type) {
        return aircraftRepository.findByType(type);
    }

    //for finding aircraft by the airline name
    public List<Aircraft> findByAirlineName(String airlineName) {
        return aircraftRepository.findByAirlineName(airlineName);
    }

    //for saving a new aircraft entry to the database.
    public Aircraft createAircraft(Aircraft newAircraft) {
        return aircraftRepository.save(newAircraft);
    }

    //for updating an existing aircraft's details.
    public Aircraft updateAircraft(long id, Aircraft updatedAircraft) {
        Aircraft aircraftToUpdate = findAircraftById(id);

        if (aircraftToUpdate != null) {
            aircraftToUpdate.setType(updatedAircraft.getType());
            aircraftToUpdate.setAirlineName(updatedAircraft.getAirlineName());
            aircraftToUpdate.setNumberOfPassengers(updatedAircraft.getNumberOfPassengers());

            return aircraftRepository.save(aircraftToUpdate);
        }

        return null;
    }
    public Aircraft addPassengerToAircraft(Long aircraftId, Long passengerId) {
        Optional<Aircraft> aircraftOpt = aircraftRepository.findById(aircraftId);
        Optional<Passenger> passengerOpt = passengerRepository.findById(passengerId);

        if (aircraftOpt.isPresent() && passengerOpt.isPresent()) {
            Aircraft aircraft = aircraftOpt.get();
            Passenger passenger = passengerOpt.get();

            // Add passenger to aircraft
            aircraft.getPassengers().add(passenger);

            // Save updated aircraft
            return aircraftRepository.save(aircraft);
        }
        return null;  // Return null if either aircraft or passenger is not found
    }}