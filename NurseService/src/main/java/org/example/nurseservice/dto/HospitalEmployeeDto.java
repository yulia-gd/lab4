package org.example.nurseservice.dto;

import org.example.nurseservice.enums.EmploymentStatus;

public record HospitalEmployeeDto(Long id, String firstName, String lastName, String email, String phone, EmploymentStatus employmentStatus ) {

}