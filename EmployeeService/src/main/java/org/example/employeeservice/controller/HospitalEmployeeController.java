package org.example.employeeservice.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.example.employeeservice.entities.HospitalEmployee;
import org.example.employeeservice.request.HospitalEmployeeUpdateRequest;
import org.example.employeeservice.response.ApiResponse;
import org.example.employeeservice.service.HospitalEmployeeService;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FOUND;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class HospitalEmployeeController {
    private final HospitalEmployeeService hospitalEmployeeService;


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getHospitalEmployeeById(@PathVariable Long id) {
        HospitalEmployee hospitalEmployee = hospitalEmployeeService.getHospitalEmployee(id);
        return ResponseEntity.ok(new ApiResponse("Hospital employee found", hospitalEmployee));
    }


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addHospitalEmployee(@Valid @RequestBody HospitalEmployee hospitalEmployee) {
        HospitalEmployee hospitalEmployeeSaved = hospitalEmployeeService.addHospitalEmployee(hospitalEmployee);

        return ResponseEntity.status(CREATED).body(new ApiResponse(
                "Hospital employee is added. Location: http://localhost:8080/api/v1/employee/" + hospitalEmployeeSaved.getId(),
                hospitalEmployeeSaved
        ));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateHospitalEmployee(@PathVariable Long id, @Valid @RequestBody HospitalEmployeeUpdateRequest hospitalEmployeeUpdateRequest) {
        HospitalEmployee updatedHospitalEmployee = hospitalEmployeeService.updateHospitalEmployee(id, hospitalEmployeeUpdateRequest);
        return ResponseEntity.ok(new ApiResponse(
                "Hospital employee is updated. Location: http://localhost:8080/api/v1/employee/" + updatedHospitalEmployee.getId(),
                updatedHospitalEmployee
        ));
    }


    @PutMapping("/fire/{id}")
    public ResponseEntity<ApiResponse> fireHospitalEmployee(@PathVariable Long id) {
        HospitalEmployee hospitalEmployee = hospitalEmployeeService.fireHospitalEmployee(id);
        return ResponseEntity.ok(new ApiResponse("Hospital employee is fired", hospitalEmployee));
    }

    @PutMapping("/hire/{id}")
    public ResponseEntity<ApiResponse> hireHospitalEmployee(@PathVariable Long id) {
        HospitalEmployee hospitalEmployee = hospitalEmployeeService.hireHospitalEmployee(id);
        return ResponseEntity.ok(new ApiResponse("Hospital employee is hired again", hospitalEmployee));
    }
}

