package com.banking.exception;

public class BadAccountData extends RuntimeException{
    public BadAccountData(String message){
        super(message);
    }
}
