package com.example.orderservice.exceptions;

/**
 * Handles BadCredentialsException
 */
public class BadCredentialsException extends RuntimeException{
    public BadCredentialsException(String message) {
        super(message);
    }
}
