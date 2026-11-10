package com.eventpro.exception;

public class TicketCategoryNotFoundException extends RuntimeException {
    public TicketCategoryNotFoundException(String message) {
        super(message);
    }
}
