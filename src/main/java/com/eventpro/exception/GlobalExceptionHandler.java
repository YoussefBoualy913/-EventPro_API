package com.eventpro.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {


   @ExceptionHandler(EvenementNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEvenementNotFoundException(EvenementNotFoundException e, HttpServletRequest request) {

       ErrorResponse errorResponse = ErrorResponse.builder()
               .status(HttpStatus.NOT_FOUND.value())
               .message(e.getMessage())
               .error(HttpStatus.NOT_FOUND.getReasonPhrase())
               .timestamp(LocalDateTime.now())
               .path(request.getRequestURI())
               .build();

       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
   }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e, HttpServletRequest request) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


}
