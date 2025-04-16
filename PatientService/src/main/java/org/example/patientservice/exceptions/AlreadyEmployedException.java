package org.example.patientservice.exceptions;

public class AlreadyEmployedException extends RuntimeException {
    public AlreadyEmployedException(String message) {
        super(message);
    }
}
