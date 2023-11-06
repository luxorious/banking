package com.banking.controller.exceptionhandler;

import com.banking.exception.BadAccountDataException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

/**
 * Global exception handler for the banking application controllers.
 */
@ControllerAdvice
@Slf4j
public class Handler {

    /**
     * Handles the BadAccountDataException and returns an Unprocessable Entity response.
     *
     * @return ResponseEntity with an Unprocessable Entity status code
     */
    @ExceptionHandler(BadAccountDataException.class)
    public ResponseEntity<String> handleBadAccountData(){
        log.error("the account has not been validated");
        return ResponseEntity.unprocessableEntity().build();
    }

    /**
     * Handles the EntityNotFoundException and returns a Not Found response.
     *
     * @return ResponseEntity with a Not Found status code
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(){
        log.error("not found");
        return ResponseEntity.notFound().build();
    }

    /**
     * Handles the IOException and returns a Bad Request response.
     *
     * @return ResponseEntity with a Bad Request status code
     */
    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(){
        log.error("file not correct");
        return ResponseEntity.badRequest().build();
    }


    /**
     * Handles generic Exception and returns a Bad Request response.
     *
     * @return ResponseEntity with a Bad Request status code
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(){
        log.error("something went wrong");
        return ResponseEntity.badRequest().build();
    }
}
