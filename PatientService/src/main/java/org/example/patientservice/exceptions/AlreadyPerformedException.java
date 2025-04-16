package org.example.patientservice.exceptions;

public class AlreadyPerformedException extends RuntimeException {
    public AlreadyPerformedException(String message) {
        super(message);
    }
}
