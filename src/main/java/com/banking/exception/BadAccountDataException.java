package com.banking.exception;

public class BadAccountDataException extends RuntimeException{
    public BadAccountDataException(String message){
        super(message);
    }
}
