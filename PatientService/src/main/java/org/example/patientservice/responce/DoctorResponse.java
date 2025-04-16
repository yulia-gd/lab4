package org.example.patientservice.responce;

import lombok.Data;
import org.example.patientservice.dto.DoctorDto;
import org.example.patientservice.dto.HospitalEmployeeDto;

@Data
public class DoctorResponse {
    private String message;
    private DoctorDto data;
}
