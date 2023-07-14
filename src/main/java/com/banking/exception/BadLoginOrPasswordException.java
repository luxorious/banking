package com.banking.exception;

public class BadLoginOrPasswordException extends RuntimeException{
    public BadLoginOrPasswordException(String message) {
        super(message);
    }
}
