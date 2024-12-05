package com.svl.servicebase.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity
public class Address {

    @Id
    @Column(name = "uuid")
    private String uuid;

    @Column(nullable = false, length = 30, updatable = false)
    private String street;

    @Column(nullable = false, length = 20, updatable = false)
    private String city;

    @Column(nullable = false, length = 5, updatable = false)
    private String postalCode;

    @Column(nullable = false, length = 20, updatable = false)
    private String country;

    @OneToMany(mappedBy = "residentialAddress")
    private Set<Person> people;
}