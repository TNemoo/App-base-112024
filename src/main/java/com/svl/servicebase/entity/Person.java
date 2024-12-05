package com.svl.servicebase.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "person")
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

    @ManyToOne
    @JoinColumn(name = "address_uuid", referencedColumnName = "uuid")
    private Address residentialAddress;

    @OneToOne(mappedBy = "person")
    private Passport passport;

    @Column(updatable = false)
    private String secretWord;

}
