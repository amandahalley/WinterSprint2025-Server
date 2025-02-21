package com.keyin.Passengers;

import jakarta.persistence.*;

// uses these JPA annotations to map the class to the database


@Entity
public class Passenger {
    @Id

    //added sequence generator to map to the database
    @SequenceGenerator(name = "passenger_sequence", sequenceName = "passenger_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "passenger_sequence")

    private Long id; //  the primary key and its generated above.
    private String firstName;
    private String lastName;
    private String phoneNumber;

    // Constructor and setters/getters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Passenger{id =" + id + ", First Name ='" + firstName + "', Last Name ='" + lastName + "', Phone Number ='" + phoneNumber + "'}";
    }
}
