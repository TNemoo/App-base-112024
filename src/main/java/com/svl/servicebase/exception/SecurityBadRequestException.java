package com.svl.servicebase.exception;

public class SecurityBadRequestException extends RuntimeException {

    public SecurityBadRequestException(String message) {
        super(message);
    }

    public SecurityBadRequestException() {
        super("This data is incorrect");
    }
}
