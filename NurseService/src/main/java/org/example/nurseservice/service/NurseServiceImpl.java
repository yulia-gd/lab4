package org.example.nurseservice.service;

import lombok.RequiredArgsConstructor;
import org.example.nurseservice.client.HospitalEmployeeClient;
import org.example.nurseservice.dto.HospitalEmployeeDto;
import org.example.nurseservice.dto.NurseDto;
import org.example.nurseservice.entities.Nurse;
import org.example.nurseservice.exceptions.ResourceNotFoundException;
import org.example.nurseservice.repository.NurseRepository;
import org.example.nurseservice.request.HospitalEmployeeAddAndUpdateRequest;
import org.example.nurseservice.request.NurseAddRequest;
import org.example.nurseservice.request.NurseUpdateRequest;
import org.example.nurseservice.responce.HospitalEmployeeResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class NurseServiceImpl implements NurseService {

    private final NurseRepository nurseRepository;
    private final HospitalEmployeeClient hospitalEmployeeClient;

    @Override
    public NurseDto getNurseByHospitalEmployeeId(Long hospitalEmployeeId){
        Nurse nurse = nurseRepository.findByHospitalEmployeeId(hospitalEmployeeId).orElseThrow(() -> new ResourceNotFoundException("Nurse not found"));
        HospitalEmployeeResponse nurseDtoResponse= hospitalEmployeeClient.getHospitalEmployeeById(nurse.getHospitalEmployeeId());
        return  new NurseDto(nurse.getId(), nurse.getQualification(),nurse.getDepartment(), nurseDtoResponse.getData());
    }
    @Override
    public NurseDto getNurseById(Long id) {
        Nurse nurse = nurseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nurse not found"));
        HospitalEmployeeResponse nurseDtoResponse= hospitalEmployeeClient.getHospitalEmployeeById(nurse.getHospitalEmployeeId());
        return  new NurseDto(nurse.getId(), nurse.getQualification(),nurse.getDepartment(), nurseDtoResponse.getData());
    }

    @Override
    public List<NurseDto> getAllNurses() {
        List<Nurse> nurses =  nurseRepository.findAll();
        List<NurseDto> nurseDtos = new ArrayList<>();
        for (Nurse nurse : nurses) {
            HospitalEmployeeDto hospitalEmployeeDto= hospitalEmployeeClient.getHospitalEmployeeById(nurse.getHospitalEmployeeId()).getData();
            NurseDto nurseDto = new NurseDto(nurse.getId(), nurse.getQualification(),nurse.getDepartment(), hospitalEmployeeDto);
            nurseDtos.add(nurseDto);
        }
        return nurseDtos;
    }

    @Override
    public NurseDto addNurse(NurseAddRequest nurseAddRequest) {
        Nurse nurse = new Nurse();
        nurse.setDepartment(nurseAddRequest.getDepartment());
        nurse.setQualification(nurseAddRequest.getQualification());
        var hospitalEmployeeDto = hospitalEmployeeClient.addHospitalEmployee(new HospitalEmployeeAddAndUpdateRequest(nurseAddRequest.getFirstName(), nurseAddRequest.getLastName(), nurseAddRequest.getEmail(), nurseAddRequest.getPhone())).getData();
        nurse.setHospitalEmployeeId( hospitalEmployeeDto.id());
        nurseRepository.save(nurse);

        return new NurseDto(nurse.getId(),  nurse.getQualification(),nurse.getDepartment(), hospitalEmployeeDto);
    }

    @Override
    public NurseDto updateNurse(Long id, NurseUpdateRequest updatedNurse) {
        Nurse nurseToUpdate = nurseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("nurse not found"));
        updateExistingNurse(nurseToUpdate, updatedNurse);
        HospitalEmployeeAddAndUpdateRequest updateRequest = new HospitalEmployeeAddAndUpdateRequest(updatedNurse.getFirstName(), updatedNurse.getLastName(), updatedNurse.getEmail(), updatedNurse.getPhone());
        HospitalEmployeeDto hospitalEmployeeDto = hospitalEmployeeClient.updateHospitalEmployee(nurseToUpdate.getId(), updateRequest).getData();
        nurseRepository.save(nurseToUpdate);
        return new NurseDto(nurseToUpdate.getId(), nurseToUpdate.getQualification(), nurseToUpdate.getDepartment(), hospitalEmployeeDto);
    }

    private void updateExistingNurse(Nurse nurseToUpdate, NurseUpdateRequest updatedNurse) {

        if(updatedNurse.getDepartment()!=null && !updatedNurse.getDepartment().isEmpty()) {
            nurseToUpdate.setDepartment(updatedNurse.getDepartment());
        }
        if(updatedNurse.getQualification()!=null){
            nurseToUpdate.setQualification(updatedNurse.getQualification());
        }
    }

    @Override
    public NurseDto fireNurse(Long id) {
        Nurse nurse = nurseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nurse not found"));
        HospitalEmployeeResponse nurseDtoResponse= hospitalEmployeeClient.fireHospitalEmployee(nurse.getHospitalEmployeeId());
        return  new NurseDto(nurse.getId(), nurse.getQualification(),nurse.getDepartment(), nurseDtoResponse.getData());
    }

    @Override
    public NurseDto hireNurse(Long id) {
        Nurse nurse = nurseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nurse not found"));
        HospitalEmployeeResponse nurseDtoResponse= hospitalEmployeeClient.hireHospitalEmployee(nurse.getHospitalEmployeeId());
        return  new NurseDto(nurse.getId(), nurse.getQualification(),nurse.getDepartment(), nurseDtoResponse.getData());
    }
}
