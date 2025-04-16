package org.example.patientservice.dto;

import org.example.patientservice.enums.NurseQualification;

public record NurseDto(Long id, NurseQualification nurseQualification, String department, HospitalEmployeeDto hospitalEmployeeDto) {
}
