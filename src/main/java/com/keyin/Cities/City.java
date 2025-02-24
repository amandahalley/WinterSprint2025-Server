package com.keyin.Cities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;
import com.keyin.Airports.Airport;  

@Entity
@Table(name = "city")
public class City {
    @Id
    //added sequence generator to map to the database
    @SequenceGenerator(name = "city_sequence", sequenceName = "city_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "city_sequence")

    private Long id;
    private String name;
    private String state;
    private int population;

    // Constructors
    public City() {}

    public City(String name, String state, int population) {
        this.name = name;
        this.state = state;
        this.population = population;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}