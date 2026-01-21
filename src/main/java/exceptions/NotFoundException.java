package com.studentmanagement.exceptions;

/**
 * Thrown when an entity (Student, Task, Course, Submission) is not found in the database.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
