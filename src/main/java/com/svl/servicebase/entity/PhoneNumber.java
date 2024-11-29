package com.svl.servicebase.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "phone_number")
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", length = 17, nullable = false, unique = true)
    private String number;

    @Column(name = "priority")
    private boolean priority = false;

    @Column(length = 100)
    private String description;

    @ManyToOne
    @JoinColumn(name = "person_uuid", referencedColumnName = "uuid")
    private Person person;

    public PhoneNumber(String number) {
        this.number = number;
        priority = true;
    }
}