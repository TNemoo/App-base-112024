package com.svl.servicebase.repository;

import com.svl.servicebase.entity.Person;
import com.svl.servicebase.entity.PersonCredentials;
import com.svl.servicebase.entity.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonCredentialsRepository extends JpaRepository<PersonCredentials, Long> {

    Optional<PersonCredentials> findByLogin(String login);

    Optional<PersonCredentials>  findByLoginAndPassword(String login, String cryptoPassword);

    boolean existsByLogin(String username);
}
