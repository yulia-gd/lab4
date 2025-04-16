package org.example.patientservice.exceptions;

public class PatientIsDischargedException extends RuntimeException {
    public PatientIsDischargedException(String message) {
        super(message);
    }
}
