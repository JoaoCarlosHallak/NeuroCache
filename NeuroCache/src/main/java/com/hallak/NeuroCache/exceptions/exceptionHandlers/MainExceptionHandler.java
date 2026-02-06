package com.hallak.NeuroCache.exceptions.exceptionHandlers;

import com.hallak.NeuroCache.exceptions.GenericApiError;
import com.hallak.NeuroCache.exceptions.UserAlreadyExistsException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;
import java.time.Instant;

@RestControllerAdvice
public class MainExceptionHandler {


    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<GenericApiError> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return new ResponseEntity<>(new GenericApiError(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), Instant.now()), HttpStatus.BAD_REQUEST);
    }

}
