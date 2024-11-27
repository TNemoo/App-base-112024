package com.svl.servicebase.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 30, nullable = false, unique = true)
    private String email;

    @Column(name = "priority")
    private boolean priority = false;

    @ManyToOne
    @JoinColumn(name = "person_uuid", referencedColumnName = "uuid")
    private Person person;
}
