package com.svl.servicebase.controller;

import com.svl.servicebase.dto.PersonCredentialsDto;
import com.svl.servicebase.service.AuthenticationService;
import com.svl.servicebase.service.SecurityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class SecurityController {

    private final AuthenticationService authenticationService;
    private final SecurityService securityService;

    @Autowired
    public SecurityController(AuthenticationService authenticationService,
                              SecurityService securityService) {
        this.authenticationService = authenticationService;
        this.securityService = securityService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> newPerson(@RequestBody @Valid PersonCredentialsDto personCredentialsDto) {

        String login = securityService.newPerson(personCredentialsDto.getLogin(), personCredentialsDto.getPassword());
        return new ResponseEntity(login, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid PersonCredentialsDto personCredentialsDto) {

        String token = authenticationService.authAndGetToken(
                personCredentialsDto.getLogin(), personCredentialsDto.getPassword());

        return ResponseEntity.ok("Bearer " + token);
    }

}
