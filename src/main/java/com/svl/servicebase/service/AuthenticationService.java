package com.svl.servicebase.service;

import com.svl.servicebase.entity.PhoneNumber;

public interface AuthenticationService {

    String authAndGetToken(String login, String password);
}
