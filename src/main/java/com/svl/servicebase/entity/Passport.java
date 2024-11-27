package com.svl.servicebase.entity;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
public class Passport {

    private String firstname;
    private String middleName;
    private String surname;

    private String series;
    private Integer number;
    private LocalDate dateOfBerth;
    private Address placeOfBirth;
    private Address placeOfRegistration;
}
