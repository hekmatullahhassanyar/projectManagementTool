package com.studentmanagement.exceptions;

/**
 * Thrown when a conflict occurs, such as duplicate entries or constraint violations.
 */
public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
