package org.example.employeeservice.service;

import lombok.RequiredArgsConstructor;

import org.example.employeeservice.entities.HospitalEmployee;
import org.example.employeeservice.enums.EmploymentStatus;
import org.example.employeeservice.exceptions.AlreadyEmployedException;
import org.example.employeeservice.exceptions.AlreadyFiredException;
import org.example.employeeservice.exceptions.ResourseNotFoundException;
import org.example.employeeservice.repository.HospitalEmployeeRepository;
import org.example.employeeservice.request.HospitalEmployeeUpdateRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalEmployeeServiceImpl implements HospitalEmployeeService {

    private final HospitalEmployeeRepository hospitalEmployeeRepository;

    @Override
    public HospitalEmployee getHospitalEmployee(Long id) {
        return hospitalEmployeeRepository.findById(id).orElseThrow(()->new ResourseNotFoundException("Employee not found"));
    }


    @Override
    public HospitalEmployee updateHospitalEmployee(Long id, HospitalEmployeeUpdateRequest updateRequest) {
        HospitalEmployee hospitalEmployee = getHospitalEmployee(id);
        updateExistingEmployee(hospitalEmployee, updateRequest);
        return hospitalEmployeeRepository.save(hospitalEmployee);
    }

    private void updateExistingEmployee(HospitalEmployee employeeToUpdate, HospitalEmployeeUpdateRequest updatedDoctor) {
        if(updatedDoctor.getFirstName()!=null && !updatedDoctor.getFirstName().isEmpty()) {
            employeeToUpdate.setFirstName(updatedDoctor.getFirstName());
        }
        if(updatedDoctor.getLastName()!=null && !updatedDoctor.getLastName().isEmpty()) {
           employeeToUpdate.setLastName(updatedDoctor.getLastName());
        }
        if(updatedDoctor.getEmail()!=null && !updatedDoctor.getEmail().isEmpty()) {
            employeeToUpdate.setEmail(updatedDoctor.getEmail());
        }
        if(updatedDoctor.getPhone()!=null && !updatedDoctor.getPhone().isEmpty()) {
            employeeToUpdate.setPhone(updatedDoctor.getPhone());
        }
    }

    @Override
    public HospitalEmployee addHospitalEmployee(HospitalEmployee hospitalEmployee) {
        hospitalEmployee.setEmploymentStatus(EmploymentStatus.EMPLOYED);
        return hospitalEmployeeRepository.save(hospitalEmployee);
    }

    @Override
    public HospitalEmployee fireHospitalEmployee(Long id) {
        HospitalEmployee hospitalEmployee = getHospitalEmployee(id);
        if(hospitalEmployee.getEmploymentStatus().equals(EmploymentStatus.FIRED)) {
            throw new AlreadyFiredException("Doctor is already fired");
        }
        hospitalEmployee.setEmploymentStatus(EmploymentStatus.FIRED);
        return hospitalEmployeeRepository.save(hospitalEmployee);

    }

    @Override
    public HospitalEmployee hireHospitalEmployee(Long id) {
        HospitalEmployee hospitalEmployee = getHospitalEmployee(id);
        if(hospitalEmployee.getEmploymentStatus().equals(EmploymentStatus.EMPLOYED)) {
            throw new AlreadyEmployedException("Doctor is already employed");
        }
        hospitalEmployee.setEmploymentStatus(EmploymentStatus.EMPLOYED);
        return hospitalEmployeeRepository.save(hospitalEmployee);

    }
}
