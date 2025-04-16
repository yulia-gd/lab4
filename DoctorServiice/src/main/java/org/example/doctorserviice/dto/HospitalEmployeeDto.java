package org.example.doctorserviice.dto;

import org.example.doctorserviice.enums.EmploymentStatus;

public record HospitalEmployeeDto(Long id, String firstName, String lastName, String email, String phone, EmploymentStatus employmentStatus ) {

}
