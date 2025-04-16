package org.example.nurseservice.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.nurseservice.dto.NurseDto;
import org.example.nurseservice.request.NurseAddRequest;
import org.example.nurseservice.request.NurseUpdateRequest;
import org.example.nurseservice.responce.ApiResponse;
import org.example.nurseservice.service.NurseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FOUND;

@RestController
@RequestMapping("api/v1/nurses")
@RequiredArgsConstructor
public class NurseController {

    private final NurseService nurseService;

    @GetMapping("hospital_employee/{id}")
    public ResponseEntity<ApiResponse> getNurseByHospitalEmployeeId(@PathVariable Long id) {
        NurseDto nurse = nurseService.getNurseByHospitalEmployeeId(id);
        return ResponseEntity.ok(new ApiResponse("Nurse found", nurse));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getNurseById(@PathVariable Long id) {
        NurseDto nurse = nurseService.getNurseById(id);
        return ResponseEntity.ok(new ApiResponse("Nurse found", nurse));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllNurses() {
        return ResponseEntity.status(FOUND).body(new ApiResponse("All nurses found", nurseService.getAllNurses()));
    }


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addNurse(@Valid @RequestBody NurseAddRequest nurse) {
        NurseDto addedNurse = nurseService.addNurse(nurse);
        return ResponseEntity.status(CREATED).body(new ApiResponse(
                "Nurse is added. Location: http://localhost:8080/api/v1/nurses/" + addedNurse.id(),
                addedNurse
        ));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateNurse(@PathVariable Long id, @Valid @RequestBody NurseUpdateRequest nurseUpdateRequest) {
        NurseDto updatedNurse = nurseService.updateNurse(id, nurseUpdateRequest);
        return ResponseEntity.ok(new ApiResponse(
                "Nurse is updated. Location: http://localhost:8080/api/v1/nurses/" + updatedNurse.id(),
                updatedNurse
        ));
    }


    @PutMapping("/fire/{id}")
    public ResponseEntity<ApiResponse> fireNurse(@PathVariable Long id) {
        NurseDto nurse = nurseService.fireNurse(id);
        return ResponseEntity.ok(new ApiResponse("Nurse is fired", nurse));
    }

    @PutMapping("/hire/{id}")
    public ResponseEntity<ApiResponse> hireNurse(@PathVariable Long id) {
        NurseDto nurse = nurseService.hireNurse(id);
        return ResponseEntity.ok(new ApiResponse("Nurse is hired again", nurse));
    }
}
