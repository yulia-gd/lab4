package org.example.nurseservice.dto;

import org.example.nurseservice.enums.NurseQualification;

public record NurseDto(Long id, NurseQualification nurseQualification, String department, HospitalEmployeeDto hospitalEmployeeDto) {
}