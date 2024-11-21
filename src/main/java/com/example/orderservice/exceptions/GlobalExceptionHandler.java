package com.example.orderservice.exceptions;

import com.example.orderservice.dto.response.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponseDto> handleNotFoundException(
      NotFoundException exception, HttpServletRequest httpServletRequest) {
    return new ResponseEntity<>(
        new ErrorResponseDto(httpServletRequest.getRequestURI(), exception.getMessage()),
        HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(IncompletePaymentException.class)
  public ResponseEntity<ErrorResponseDto> handleIncompletePaymentException(
      IncompletePaymentException exception, HttpServletRequest httpServletRequest) {
    return new ResponseEntity<>(
        new ErrorResponseDto(httpServletRequest.getRequestURI(), exception.getMessage()),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDto> handleBadCredentialsException(
        BadCredentialsException exception, HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(
            new ErrorResponseDto(httpServletRequest.getRequestURI(), exception.getMessage()),
            HttpStatus.UNAUTHORIZED);
    }
}
