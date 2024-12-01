package com.svl.servicebase.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class PersonCredentials {

    @Id
    private String personUuid;

    @NotBlank
    @Column(unique = true)
    private String login;

    @NotBlank
    @Column(nullable = false, length = 60)
    private String password;

    @ElementCollection
    private Set<Role> roles = new HashSet<>();

    @Column
    private Boolean enabled = true;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    public PersonCredentials(String login, String password) {
        this.personUuid = String.valueOf(UUID.randomUUID());
        this.login = login;
        this.password = password;
        this.roles.add(Role.User);
        this.createdAt = LocalDateTime.now();
    }
}
