package com.banking.controller.exceptionHandler;

import com.banking.exception.BadAccountData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class Handler {
    @ExceptionHandler(BadAccountData.class)
    public ResponseEntity<String> handleBadAccountData(Exception e){
        log.error("the account has not been validated");
        return ResponseEntity.notFound().build();
    }
}
