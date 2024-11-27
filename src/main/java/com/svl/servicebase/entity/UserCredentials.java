package com.svl.servicebase.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class UserCredentials {

    @Id
    private String personUuid;

    @OneToOne
    @JoinColumn(name = "phone_number_id", referencedColumnName = "id")
    private PhoneNumber login;

    @Column(nullable = false, length = 60)
    private String password;

    @ElementCollection
    private Set<Role> roles = new HashSet<>();
}
