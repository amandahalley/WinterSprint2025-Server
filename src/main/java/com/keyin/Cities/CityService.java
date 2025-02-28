package com.keyin.Cities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public Optional<City> findCityById(Long id) {
        return cityRepository.findById(id);
    }

    public City createCity(City city) {
        return cityRepository.save(city);
    }

    public City updateCity(Long id, City updatedCity) {
        if (cityRepository.existsById(id)) {
            updatedCity.setId(id);
            return cityRepository.save(updatedCity);
        }
        return null;
    }

    public boolean deleteCity(Long id) {
        if (cityRepository.existsById(id)) {
            cityRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public List<City> searchCities(String name, String state, Integer population) {
        List<City> cities = cityRepository.findAll(); // Ensures result is a List

        return cities.stream()
                .filter(city -> (name == null || city.getName().equalsIgnoreCase(name)) &&
                                (state == null || city.getState().equalsIgnoreCase(state)) &&
                                (population == null || city.getPopulation() == population))
                .collect(Collectors.toList());
    }
}
