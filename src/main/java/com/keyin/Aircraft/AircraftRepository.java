package com.keyin.Aircraft;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AircraftRepository extends CrudRepository<Aircraft, Long> {
    List<Aircraft> findByType(String type);
    List<Aircraft> findByAirlineName(String airlineName);
}
