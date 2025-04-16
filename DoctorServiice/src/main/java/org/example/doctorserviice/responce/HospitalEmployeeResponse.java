package org.example.doctorserviice.responce;

import lombok.Getter;
import lombok.Setter;
import org.example.doctorserviice.dto.HospitalEmployeeDto;

@Setter
@Getter
public class HospitalEmployeeResponse {
    private String message;
    private HospitalEmployeeDto data;
}
