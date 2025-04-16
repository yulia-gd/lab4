package org.example.patientservice.responce;

import lombok.Getter;
import lombok.Setter;
import org.example.patientservice.dto.HospitalEmployeeDto;

@Setter
@Getter
public class HospitalEmployeeResponse {
    private String message;
    private HospitalEmployeeDto data;
}