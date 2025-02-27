package com.keyin.Airports;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import com.keyin.Aircraft.Aircraft;
import com.keyin.Passengers.Passenger;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Airport {
    @Id
    @SequenceGenerator(name = "airport_sequence", sequenceName = "airport_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "airport_sequence")

    private long id;
    private String code;
    private String name;

    @ManyToMany(mappedBy = "airports")
    private List<Aircraft> aircrafts;

    @ManyToMany(mappedBy = "airports")
    private List<Passenger> passengers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Aircraft> getAircrafts() {
        return aircrafts;
    }

    public void setAircrafts(List<Aircraft> aircrafts) {
        this.aircrafts = aircrafts;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
}
