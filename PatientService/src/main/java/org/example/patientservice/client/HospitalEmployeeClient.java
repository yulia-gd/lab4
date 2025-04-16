package org.example.patientservice.client;

import org.example.patientservice.request.HospitalEmployeeAddAndUpdateRequest;
import org.example.patientservice.responce.HospitalEmployeeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "HOSPITALEMPLOYEE")
public interface HospitalEmployeeClient {

    @GetMapping("/api/v1/employee/{id}")
    HospitalEmployeeResponse getHospitalEmployeeById(@PathVariable("id") Long id);

    @PutMapping("/api/v1/employee/fire/{id}")
    HospitalEmployeeResponse fireHospitalEmployee(@PathVariable("id") Long id);

    @PutMapping("/api/v1/employee/hire/{id}")
    HospitalEmployeeResponse hireHospitalEmployee(@PathVariable("id") Long id);

    @PostMapping("/api/v1/employee/add")
    HospitalEmployeeResponse addHospitalEmployee(@RequestBody HospitalEmployeeAddAndUpdateRequest hospitalEmployee);

    @PutMapping("/api/v1/employee/update/{id}")
    HospitalEmployeeResponse updateHospitalEmployee(@PathVariable("id") Long id, @RequestBody HospitalEmployeeAddAndUpdateRequest hospitalEmployeeAddAndUpdateRequest);
}