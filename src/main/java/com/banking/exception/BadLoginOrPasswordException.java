package com.banking.exception;

/**
 * Custom exception class for handling bad login or password data in the banking application.
 */
public class BadLoginOrPasswordException extends RuntimeException {

    /**
     * Constructs a new BadLoginOrPasswordException with the specified error message.
     *
     * @param message The error message for the exception.
     */
    public BadLoginOrPasswordException(String message) {
        super(message);
    }
}
