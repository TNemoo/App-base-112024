package com.svl.servicebase.service;

import com.svl.servicebase.dto.PersonCredentialsDto;
import org.springframework.transaction.annotation.Transactional;

public interface SecurityService {
    @Transactional
    String newPerson(String login, String password);

    @Transactional
    String authAndGetToken(PersonCredentialsDto personCredentialsDto);
}
