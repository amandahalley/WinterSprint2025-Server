package com.keyin.Passengers;

import com.keyin.Aircraft.Aircraft;
import com.keyin.Airports.Airport;
import com.keyin.Cities.City;
import jakarta.persistence.*;

import java.util.List;

// uses these JPA annotations to map the class to the database


@Entity
public class Passenger {
    @Id

    //added sequence generator to map to the database
    @SequenceGenerator(name = "passenger_sequence", sequenceName = "passenger_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "passenger_sequence")

    private Long id; //  the primary key and its generated above.
    private String firstName;
    private String lastName;
    private String phoneNumber;

    // Many to many Aircraft
    @ManyToMany
    @JoinTable(
            name = "passenger_aircraft",
            joinColumns = @JoinColumn(name = "passenger_id"),
            inverseJoinColumns = @JoinColumn(name = "aircraft_id")
    )
    private List<Aircraft> aircrafts;

    // Many to many Airport
    @ManyToMany
    @JoinTable(
            name = "passenger_airport",
            joinColumns = @JoinColumn(name = "passenger_id"),
            inverseJoinColumns = @JoinColumn(name = "airport_id")
    )
    private List<Airport> airports;

    // passengers belong to one city
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;


    // Constructor and setters/getters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Aircraft> getAircrafts() {
        return aircrafts;
    }

    public void setAircrafts(List<Aircraft> aircrafts) {
        this.aircrafts = aircrafts;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }


    @Override
    public String toString() {
        return "Passenger{id =" + id + ", First Name ='" + firstName + "', Last Name ='" + lastName + "', Phone Number ='" + phoneNumber + "', City = " + city.getName() + "'}";
    }
}
