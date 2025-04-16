package org.example.employeeservice.exceptions;

public class AlreadyFiredException extends RuntimeException {
    public AlreadyFiredException(String message) {
        super(message);
    }
}
