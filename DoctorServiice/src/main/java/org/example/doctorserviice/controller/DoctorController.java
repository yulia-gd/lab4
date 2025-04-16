package org.example.doctorserviice.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.example.doctorserviice.dto.DoctorDto;
import org.example.doctorserviice.entities.Doctor;
import org.example.doctorserviice.request.DoctorAddRequest;
import org.example.doctorserviice.request.DoctorUpdateRequest;
import org.example.doctorserviice.responce.ApiResponse;
import org.example.doctorserviice.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FOUND;

@RestController
@RequestMapping("api/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getDoctorById(@PathVariable Long id) {
        DoctorDto doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(new ApiResponse("Doctor found", doctor));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllDoctors() {
        return ResponseEntity.status(FOUND).body(new ApiResponse("All doctors found", doctorService.getAllDoctors()));
    }


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addDoctor(@Valid @RequestBody DoctorAddRequest doctor) {
        DoctorDto addedDoctor = doctorService.addDoctor(doctor);
        return ResponseEntity.status(CREATED).body(new ApiResponse(
                "Doctor is added. Location: http://localhost:8080/api/v1/doctors/" + addedDoctor.id(),
                addedDoctor
        ));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateDoctor(@PathVariable Long id, @Valid @RequestBody DoctorUpdateRequest doctorUpdateRequest) {
        DoctorDto updatedDoctor = doctorService.updateDoctor(id, doctorUpdateRequest);
        return ResponseEntity.ok(new ApiResponse(
                "Doctor is updated. Location: http://localhost:8080/api/v1/doctors/" + updatedDoctor.id(),
                updatedDoctor
        ));
    }


    @PutMapping("/fire/{id}")
    public ResponseEntity<ApiResponse> fireDoctor(@PathVariable Long id) {
        DoctorDto doctor = doctorService.fireDoctor(id);
        return ResponseEntity.ok(new ApiResponse("Doctor is fired", doctor));
    }

    @PutMapping("/hire/{id}")
    public ResponseEntity<ApiResponse> hireDoctor(@PathVariable Long id) {
        DoctorDto doctor = doctorService.hireDoctor(id);
        return ResponseEntity.ok(new ApiResponse("Doctor is hired again", doctor));
    }
}
