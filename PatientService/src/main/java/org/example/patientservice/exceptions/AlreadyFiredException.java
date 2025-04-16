package org.example.patientservice.exceptions;

public class AlreadyFiredException extends RuntimeException {
    public AlreadyFiredException(String message) {
        super(message);
    }
}
