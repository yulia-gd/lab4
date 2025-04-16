package org.example.employeeservice.service;


import org.example.employeeservice.entities.HospitalEmployee;
import org.example.employeeservice.request.HospitalEmployeeUpdateRequest;

public interface HospitalEmployeeService {
    HospitalEmployee getHospitalEmployee(Long id);
    HospitalEmployee updateHospitalEmployee(Long id, HospitalEmployeeUpdateRequest updateRequest);
    HospitalEmployee addHospitalEmployee(HospitalEmployee hospitalEmployee);
    HospitalEmployee fireHospitalEmployee(Long id);
    HospitalEmployee hireHospitalEmployee(Long id);
}
