package com.keyin.Cities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> getAllCities() {
        return (List<City>) cityRepository.findAll();
    }

    public Optional<City> findCityById(Long id) {
        return cityRepository.findById(id);
    }

    public City createCity(City newCity) {
        return cityRepository.save(newCity);
    }

    public void deleteCity(Long id) {

        cityRepository.deleteById(id);
    }

    public List<City> findByState(String state) {
        return cityRepository.findByState(state);
    }

    public List<City> findByPopulation(int population) {
        return cityRepository.findByPopulation(population);
    }


    //added in this find city by name
    public City findByName(String name) {
        return cityRepository.getCityByName(name);
    }

    public City updateCity(Long id, City updatedCity) {
        Optional<City> cityToUpdate = findCityById(id);

        if (cityToUpdate.isPresent()) {
            City city = cityToUpdate.get();
            city.setName(updatedCity.getName());
            city.setState(updatedCity.getState());   // update state
            city.setPopulation(updatedCity.getPopulation()); // update population
            return cityRepository.save(city);
        }
        return null;
    }

}