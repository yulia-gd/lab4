package org.example.employeeservice.exceptions;

public class AlreadyEmployedException extends RuntimeException {
    public AlreadyEmployedException(String message) {
        super(message);
    }
}
