package org.example.nurseservice.service;

import org.example.nurseservice.dto.NurseDto;
import org.example.nurseservice.request.NurseAddRequest;
import org.example.nurseservice.request.NurseUpdateRequest;

import java.util.List;

public interface NurseService {
    NurseDto getNurseByHospitalEmployeeId(Long hospitalEmployeeId);

    NurseDto getNurseById(Long id);

    List<NurseDto> getAllNurses();

    NurseDto addNurse(NurseAddRequest doctor);

    NurseDto updateNurse(Long id, NurseUpdateRequest updatedDoctor);


    NurseDto fireNurse(Long id);

    NurseDto hireNurse(Long id);

}
