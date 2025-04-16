package org.example.patientservice.client;

import org.example.patientservice.responce.DoctorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DOCTOR")
public interface DoctorClient {
    @GetMapping("api/v1/doctors/{id}")
    DoctorResponse getDoctorById(@PathVariable("id") Long id);
}
