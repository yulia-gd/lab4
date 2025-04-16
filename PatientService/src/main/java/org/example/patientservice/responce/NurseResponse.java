package org.example.patientservice.responce;

import lombok.Data;
import org.example.patientservice.dto.DoctorDto;
import org.example.patientservice.dto.NurseDto;

@Data
public class NurseResponse {
    private String message;
    private NurseDto data;
}
