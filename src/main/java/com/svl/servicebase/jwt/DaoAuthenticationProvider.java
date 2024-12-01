package com.svl.servicebase.jwt;


import com.svl.servicebase.entity.PersonCredentials;
import com.svl.servicebase.exception.SecurityBadRequestException;
import com.svl.servicebase.repository.PersonCredentialsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DaoAuthenticationProvider implements UserDetailsService {

    private final PersonCredentialsRepository personCredentialsRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        PersonCredentials person = personCredentialsRepository.findByLogin(login)
                .orElseThrow(() -> new SecurityBadRequestException("User with login:" + login + "not found"));

        JwtPerson jwtUser = new JwtPerson(person);

        log.info("IN loadUserByUsername -  loaded login: {} successfully loaded", login);
        return jwtUser;
    }
}
