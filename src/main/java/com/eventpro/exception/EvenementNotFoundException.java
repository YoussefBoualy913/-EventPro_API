package com.eventpro.exception;

public class EvenementNotFoundException extends RuntimeException {
    public EvenementNotFoundException(String message) {
        super(message);
    }
}
