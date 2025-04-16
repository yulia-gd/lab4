package org.example.nurseservice.repository;

import org.example.nurseservice.entities.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NurseRepository extends JpaRepository<Nurse, Long> {

    Optional<Nurse> findByHospitalEmployeeId(Long id);
}
