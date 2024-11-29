package com.svl.servicebase.exception;

import org.springframework.security.core.AuthenticationException;

/* Поскольку AuthenticationException абстрактный класс, нужно унаследовать или создавать каждый раз анонимный класс */
public class AuthException extends AuthenticationException {
    public AuthException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AuthException(String msg) {
        super(msg);
    }

    public AuthException() {
        super("Authentication method not supported");
    }
}
