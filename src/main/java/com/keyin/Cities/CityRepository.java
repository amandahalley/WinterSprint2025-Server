package com.keyin.Cities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findByNameIgnoreCase(String name);  // Optional: Custom Query Method
    List<City> findByStateIgnoreCase(String state);
    List<City> findByPopulation(int population);
}
