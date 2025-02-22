package com.keyin.Aircraft;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Aircraft {
    @Id
    @SequenceGenerator(name = "aircracft_sequence", sequenceName = "aircraft_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "aircraft_sequence")

    private long id;
    private String type;
    private String airlineName;
    private int numberOfPassengers;

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
}
