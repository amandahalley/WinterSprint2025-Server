package com.keyin.Aircraft;

import com.keyin.Passengers.Passenger;
import com.keyin.Airports.Airport;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Aircraft {
    @Id
    @SequenceGenerator(name = "aircraft_sequence", sequenceName = "aircraft_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "aircraft_sequence")

    private long id;
    private String type;
    private String airlineName;
    private int numberOfPassengers;


    @ManyToMany
    @JoinTable(
        name = "aircraft_passenger",
        joinColumns = @JoinColumn(name = "aircraft_id"),
        inverseJoinColumns = @JoinColumn(name = "passenger_id")
    )
    private Set<Passenger> passengers;
    
    @ManyToOne
    @JoinColumn(name = "airport_id", referencedColumnName = "id")
    private Airport airport;


    private List<Airport> airports;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }
    public Set<Passenger> getPassengers() { return passengers; }  
    public void setPassengers(Set<Passenger> passengers) { this.passengers = passengers; }
}

