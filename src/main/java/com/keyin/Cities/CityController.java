package com.keyin.Cities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin

public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/cities")
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/city/{id}")
    public City getCityById(@PathVariable Long id) {
        return cityService.findCityById(id).orElse(null); // If city doesn't exist, returns null
    }

    // added in searching a city by name, state, and population
    @GetMapping("/city_search")
    public List<City> getCityByName(@RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "state", required = false) String state,
                                    @RequestParam(value = "population", required = false) Integer population) {
        List<City> results = new ArrayList<>();
        if (name != null) {
            City city = cityService.findByName(name);
            if (city != null) {
                results.add(city);
            }
        } else if (state != null) {
            results.addAll(cityService.findByState(state));
        } else if (population != null) {
            results.addAll(cityService.findByPopulation(population));
        }
        return results;
    }

    @PostMapping("/city")
    public City createCity(@RequestBody City city) {
        return cityService.createCity(city);
    }

    @PutMapping("/city/{id}") //added this for updating a city
    public City updateCity(@PathVariable Long id, @RequestBody City updatedCity){
        return cityService.updateCity(id, updatedCity);
    }
    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
    }
}