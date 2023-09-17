package com.lilkhalil.test.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.lilkhalil.test.exceptions.EmptyStringException;

@RestControllerAdvice
public class CharacterControllerAdvice {
    
    @ExceptionHandler(EmptyStringException.class)
    public ResponseStatusException handleEmptyStringException(EmptyStringException ex) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
    }

}
