package com.example.orderservice.exceptions;

public class IncompletePaymentException extends RuntimeException{
    public IncompletePaymentException(String message) {
        super(message);
    }
}
