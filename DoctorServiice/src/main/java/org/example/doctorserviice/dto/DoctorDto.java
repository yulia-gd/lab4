package org.example.doctorserviice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DoctorDto(Long id, String specialization, HospitalEmployeeDto hospitalEmployeeDto) {
}
