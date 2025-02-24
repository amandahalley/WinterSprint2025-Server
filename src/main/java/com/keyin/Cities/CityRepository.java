package com.keyin.Cities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
    City getCityByName(String name);
    List<City> findByState(String state);
    List<City> findByPopulation(int population);
}