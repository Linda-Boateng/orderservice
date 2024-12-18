package com.example.orderservice.exceptions;

/**
 * Handles IncompletePaymentException
 */
public class IncompletePaymentException extends RuntimeException{
    public IncompletePaymentException(String message) {
        super(message);
    }
}
