package com.banking.exception;
/**
 * Custom exception class for handling bad list data in the banking application.
 */
public class BadListException extends RuntimeException{

    /**
     * Constructs a new BadListException with the specified error message.
     *
     * @param message The error message for the exception.
     */
    public BadListException(String message){
        super(message);
    }
}
