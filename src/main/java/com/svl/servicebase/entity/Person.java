package com.svl.servicebase.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Person {

    @Id
    private String uuid;

    @Column(name = "firstname", length = 30, updatable = false)
    private String firstname;

    @Column(name = "middle_name", length = 30, updatable = false)
    private String middleName;

    @Column(name = "surname", length = 30, updatable = false)
    private String surname;

    @Column(name = "date_of_registration", updatable = false, insertable = false)
    private LocalDate dateOfRegistration;

    @OneToMany(mappedBy = "person")
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    @OneToMany(mappedBy = "person")
    private List<Email> email = new ArrayList<>();

    @Embedded
    @Column(name = "residential_address")
    private Address residentialAddress;

    @Embedded
    @Column(unique = true)
    private Passport passport;

    @Column(updatable = false)
    private String secretWord;

}
