package com.banking.exception;

/**
 * Custom exception class for handling bad account data in the banking application.
 */
public class BadAccountDataException extends RuntimeException{
    /**
     * Constructs a new BadAccountDataException with the specified error message.
     *
     * @param message The error message for the exception.
     */
    public BadAccountDataException(String message){
        super(message);
    }
}
