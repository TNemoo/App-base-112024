package com.svl.servicebase.jwt;

import com.svl.servicebase.entity.PersonCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtPerson implements UserDetails {

    private final PersonCredentials personCredentials;

    @Autowired
    public JwtPerson(PersonCredentials personCredentials) {
        this.personCredentials = personCredentials;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return personCredentials.getRoles().stream().map(a -> new SimpleGrantedAuthority(a.toString())).toList();
    }

    @Override
    public String getPassword() {
        return this.personCredentials.getPassword();
    }

    @Override
    public String getUsername() {
        return this.personCredentials.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return personCredentials.getEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return personCredentials.getEnabled();
    }

    // геттер, чтобы получать данные аутентифицированного пользователя
    public PersonCredentials getPersonCredentials() {
        return personCredentials;
    }
}
