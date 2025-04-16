package org.example.doctorserviice.repository;


import org.example.doctorserviice.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
