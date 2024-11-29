package com.svl.servicebase.service;

import com.svl.servicebase.entity.PersonCredentials;
import com.svl.servicebase.entity.PhoneNumber;
import com.svl.servicebase.exception.SecurityBadRequestException;
import com.svl.servicebase.jwt.JwtTokenProvider;
import com.svl.servicebase.repository.PersonCredentialsRepository;
import com.svl.servicebase.repository.PersonRepository;
import com.svl.servicebase.repository.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecurityService {

    private final PhoneNumberRepository phoneNumberRepository;
    private final PersonCredentialsRepository personCredentialsRepository;
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public SecurityService(
            PersonRepository personRepository,
            PhoneNumberRepository phoneNumberRepository,
            PersonCredentialsRepository personCredentialsRepository,
            PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.personRepository = personRepository;
        this.phoneNumberRepository = phoneNumberRepository;
        this.personCredentialsRepository = personCredentialsRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Transactional
    public String newPerson(String login, String password) {

        phoneNumberRepository.findByNumber(login).
                orElseThrow(() -> new SecurityBadRequestException("this phone number is already exist"));

        PersonCredentials personCredentials = new PersonCredentials(login, passwordEncoder.encode(password));
        personCredentialsRepository.save(personCredentials);
        return login;
    }
}
