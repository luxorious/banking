package com.banking.controller.exceptionhandler;

import com.banking.exception.BadAccountData;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
@Slf4j
public class Handler {
    @ExceptionHandler(BadAccountData.class)
    public ResponseEntity<String> handleBadAccountData(){
        log.error("the account has not been validated");
        return ResponseEntity.unprocessableEntity().build();
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(){
        log.error("not found");
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(){
        log.error("file not correct");
        return ResponseEntity.badRequest().build();
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(){
        log.error("something went wrong");
        return ResponseEntity.badRequest().build();
    }
}
