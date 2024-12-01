package com.svl.servicebase.service;

import com.svl.servicebase.dto.PersonCredentialsDto;
import com.svl.servicebase.entity.PersonCredentials;
import com.svl.servicebase.exception.SecurityBadRequestException;
import com.svl.servicebase.jwt.JwtTokenProvider;
import com.svl.servicebase.repository.PersonCredentialsRepository;
import com.svl.servicebase.repository.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final PhoneNumberRepository phoneNumberRepository;
    private final PersonCredentialsRepository personCredentialsRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public SecurityServiceImpl(
            PhoneNumberRepository phoneNumberRepository,
            PersonCredentialsRepository personCredentialsRepository,
            PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.phoneNumberRepository = phoneNumberRepository;
        this.personCredentialsRepository = personCredentialsRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    @Transactional
    public String newPerson(String login, String password) {

        phoneNumberRepository.findByNumber(login).
                orElseThrow(() -> new SecurityBadRequestException("this phone number is already exist"));

        PersonCredentials personCredentials = new PersonCredentials(login, passwordEncoder.encode(password));
        personCredentialsRepository.save(personCredentials);
        return login;
    }

    @Override
    @Transactional
    public String authAndGetToken(PersonCredentialsDto personCredentialsDto) {
        PersonCredentials personCredentials = personCredentialsRepository.findByLogin(personCredentialsDto.getLogin())
                .orElseThrow(SecurityBadRequestException::new);

        if (!passwordEncoder.matches(personCredentialsDto.getPassword(), personCredentials.getPassword()))
            throw new SecurityBadRequestException();

        return jwtTokenProvider.createToken(personCredentials);
    }
}
