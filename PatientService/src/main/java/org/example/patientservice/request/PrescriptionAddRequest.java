package org.example.patientservice.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.patientservice.enums.PrescriptionType;

@Data
public class PrescriptionAddRequest {

    @NotBlank(message = "Prescription must have description")
    private String description;

    @NotNull(message = "Prescription must have type")
    private PrescriptionType type;
}