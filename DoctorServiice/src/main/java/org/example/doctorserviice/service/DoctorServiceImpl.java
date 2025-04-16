package org.example.doctorserviice.service;

import lombok.RequiredArgsConstructor;
import org.example.doctorserviice.client.HospitalEmployeeClient;
import org.example.doctorserviice.dto.DoctorDto;
import org.example.doctorserviice.dto.HospitalEmployeeDto;
import org.example.doctorserviice.entities.Doctor;
import org.example.doctorserviice.repository.DoctorRepository;
import org.example.doctorserviice.ecxeptions.ResourceNotFoundException;
import org.example.doctorserviice.request.DoctorAddRequest;
import org.example.doctorserviice.request.DoctorUpdateRequest;
import org.example.doctorserviice.request.HospitalEmployeeAddAndUpdateRequest;
import org.example.doctorserviice.responce.HospitalEmployeeResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final HospitalEmployeeClient hospitalEmployeeClient;
    @Override
    public DoctorDto getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        HospitalEmployeeResponse doctorDtoResponse= hospitalEmployeeClient.getHospitalEmployeeById(doctor.getHospitalEmployeeId());
        return  new DoctorDto(doctor.getId(), doctor.getSpecialization(), doctorDtoResponse.getData());
    }

    @Override
    public List<DoctorDto> getAllDoctors() {
        List<Doctor> doctors =  doctorRepository.findAll();
        List<DoctorDto> doctorDtos = new ArrayList<>();
        for (Doctor doctor : doctors) {
            HospitalEmployeeDto hospitalEmployeeDto= hospitalEmployeeClient.getHospitalEmployeeById(doctor.getHospitalEmployeeId()).getData();
            DoctorDto doctorDto = new DoctorDto(doctor.getId(), doctor.getSpecialization(), hospitalEmployeeDto);
            doctorDtos.add(doctorDto);
        }
        return doctorDtos;
    }

    @Override
    public DoctorDto addDoctor(DoctorAddRequest doctorAddRequest) {
        Doctor doctor = new Doctor();
        doctor.setSpecialization(doctorAddRequest.getSpecialization());
        var hospitalEmployeeDto = hospitalEmployeeClient.addHospitalEmployee(new HospitalEmployeeAddAndUpdateRequest(doctorAddRequest.getFirstName(), doctorAddRequest.getLastName(), doctorAddRequest.getEmail(), doctorAddRequest.getPhone())).getData();
        doctor.setHospitalEmployeeId( hospitalEmployeeDto.id());
        doctorRepository.save(doctor);

        return new DoctorDto(doctor.getId(), doctor.getSpecialization(), hospitalEmployeeDto);
    }

    @Override
    public DoctorDto updateDoctor(Long id, DoctorUpdateRequest updatedDoctor) {
        Doctor doctorToUpdate = doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        updateExistingDoctor(doctorToUpdate, updatedDoctor);
        HospitalEmployeeAddAndUpdateRequest updateRequest = new HospitalEmployeeAddAndUpdateRequest(updatedDoctor.getFirstName(), updatedDoctor.getLastName(), updatedDoctor.getEmail(), updatedDoctor.getPhone());
        HospitalEmployeeDto hospitalEmployeeDto = hospitalEmployeeClient.updateDoctor(doctorToUpdate.getId(), updateRequest).getData();
        doctorRepository.save(doctorToUpdate);
        return new DoctorDto(doctorToUpdate.getId(), doctorToUpdate.getSpecialization(), hospitalEmployeeDto);
    }

    private void updateExistingDoctor(Doctor doctorToUpdate, DoctorUpdateRequest updatedDoctor) {

        if(updatedDoctor.getSpecialization()!=null && !updatedDoctor.getSpecialization().isEmpty()) {
            doctorToUpdate.setSpecialization(updatedDoctor.getSpecialization());
        }
    }

    @Override
    public DoctorDto fireDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        HospitalEmployeeResponse doctorDtoResponse= hospitalEmployeeClient.fireHospitalEmployee(doctor.getHospitalEmployeeId());
        return  new DoctorDto(doctor.getId(), doctor.getSpecialization(), doctorDtoResponse.getData());
    }

    @Override
    public DoctorDto hireDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        HospitalEmployeeResponse doctorDtoResponse= hospitalEmployeeClient.hireHospitalEmployee(doctor.getHospitalEmployeeId());
        return  new DoctorDto(doctor.getId(), doctor.getSpecialization(), doctorDtoResponse.getData());
    }
}
