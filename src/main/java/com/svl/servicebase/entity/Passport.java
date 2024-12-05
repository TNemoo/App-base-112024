package com.svl.servicebase.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Data
@Entity
public class Passport {

    @Id
    private String uuid;

    @Column
    private String series;

    @Column
    private Integer number;

    @Column
    private LocalDate dateOfBerth;

    @ManyToOne
    @JoinColumn(name = "birth_address_uuid", referencedColumnName = "uuid")
    private Address placeOfBirth;

    @ManyToOne
    @JoinColumn(name = "registration_address_uuid", referencedColumnName = "uuid")
    private Address placeOfRegistration;

    @OneToOne
    @JoinColumn(name = "person_uuid", referencedColumnName = "uuid")
    private Person person;
}
