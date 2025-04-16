package org.example.nurseservice.responce;


import lombok.Getter;
import lombok.Setter;
import org.example.nurseservice.dto.HospitalEmployeeDto;

@Setter
@Getter
public class HospitalEmployeeResponse {
    private String message;
    private HospitalEmployeeDto data;
}

