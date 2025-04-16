package org.example.doctorserviice.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.doctorserviice.enums.EmploymentStatus;

public record HospitalEmployeeDto(Long id, String firstName, String lastName, String email, String phone, EmploymentStatus employmentStatus ) {

}
