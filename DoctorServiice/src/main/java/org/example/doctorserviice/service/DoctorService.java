package org.example.doctorserviice.service;

import org.example.doctorserviice.dto.DoctorDto;
import org.example.doctorserviice.entities.Doctor;
import org.example.doctorserviice.request.DoctorAddRequest;
import org.example.doctorserviice.request.DoctorUpdateRequest;

import java.util.List;

public interface DoctorService {
    DoctorDto getDoctorById(Long id);

    List<DoctorDto> getAllDoctors();

    DoctorDto addDoctor(DoctorAddRequest doctor);

    DoctorDto updateDoctor(Long id, DoctorUpdateRequest updatedDoctor);


    DoctorDto fireDoctor(Long id);

    DoctorDto hireDoctor(Long id);


}
