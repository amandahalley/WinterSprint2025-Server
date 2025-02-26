package com.keyin.Passengers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    // will help with using operations such as save(), findAll(), delete... etc.
    // it will handle all Passenger entities as I have set above
    // use Long as primary key which is the ID set in the Passenger file.
}
