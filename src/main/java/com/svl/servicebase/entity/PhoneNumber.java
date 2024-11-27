package com.svl.servicebase.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "phone_number")
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number", length = 17, nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "priority")
    private boolean priority = false;

    @Column(length = 100)
    private String description;

    @ManyToOne
    @JoinColumn(name = "person_uuid", referencedColumnName = "uuid")
    private Person person;

}