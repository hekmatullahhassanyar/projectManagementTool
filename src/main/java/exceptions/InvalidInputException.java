package com.studentmanagement.exceptions;

/**
 * Thrown when invalid or malformed input is provided to the system.
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}
