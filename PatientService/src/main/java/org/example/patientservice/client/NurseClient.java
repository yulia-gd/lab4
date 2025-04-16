package org.example.patientservice.client;

import org.example.patientservice.responce.NurseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "NURSE")
public interface NurseClient {

    @GetMapping("api/v1/nurses/hospital_employee/{id}")
    NurseResponse getNurseByHospitalEmployeeId(@PathVariable("id") Long id);
}
