package org.example.patientservice.dto;

import org.example.patientservice.enums.EmploymentStatus;

public record HospitalEmployeeDto(Long id, String firstName, String lastName, String email, String phone, EmploymentStatus employmentStatus ) {

}