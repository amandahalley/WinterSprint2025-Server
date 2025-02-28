package com.keyin.Passengers;

import com.keyin.Aircraft.Aircraft;
import com.keyin.Airports.Airport;
import com.keyin.Cities.City;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable; // ✅ Import Serializable
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "passenger")
public class Passenger implements Serializable { // ✅ Implement Serializable
    private static final long serialVersionUID = 1L; // ✅ Add serialVersionUID

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ✅ Use IDENTITY for MySQL
    private Long id;

    private String firstName;
    private String lastName;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "airport_id")
    @JsonIgnore
    private Airport airport;

    @ManyToMany
    @JoinTable(
        name = "passenger_aircraft",
        joinColumns = @JoinColumn(name = "passenger_id"),
        inverseJoinColumns = @JoinColumn(name = "aircraft_id")
    )
    @JsonIgnore
    private Set<Aircraft> aircraft = new HashSet<>();


    public Passenger() {}

    public Passenger(String firstName, String lastName, String phoneNumber, City city, Airport airport) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.airport = airport;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public City getCity() { return city; }
    public void setCity(City city) { this.city = city; }

    public Airport getAirport() { return airport; }
    public void setAirport(Airport airport) { this.airport = airport; }

    public Set<Aircraft> getAircraft() { return aircraft; }
    public void setAircraft(Set<Aircraft> aircraft) { this.aircraft = aircraft; }

    public void addAircraft(Aircraft aircraft) {
        this.aircraft.add(aircraft);
        aircraft.getPassengers().add(this);
    }

    public void removeAircraft(Aircraft aircraft) {
        this.aircraft.remove(aircraft);
        aircraft.getPassengers().remove(this);
    }

    @Override
    public String toString() {
        return "Passenger{" +
            "id=" + id +
            ", First Name='" + firstName + '\'' +
            ", Last Name='" + lastName + '\'' +
            ", Phone Number='" + phoneNumber + '\'' +
            ", City=" + (city != null ? city.getName() : "N/A") +
            ", Airport=" + (airport != null ? airport.getName() : "N/A") +
            '}';
    }
}
