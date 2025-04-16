package org.example.patientservice.dto;

public record DoctorDto(Long id, String specialization, HospitalEmployeeDto hospitalEmployeeDto) {
}
