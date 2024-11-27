package com.svl.servicebase.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Embeddable
public class Address {

    @Column(nullable = false, length = 30, updatable = false)
    private String street;

    @Column(nullable = false, length = 20, updatable = false)
    private String city;

    @Column(nullable = false, length = 5, updatable = false)
    private String postalCode;

    @Column(nullable = false, length = 20, updatable = false)
    private String country;
}