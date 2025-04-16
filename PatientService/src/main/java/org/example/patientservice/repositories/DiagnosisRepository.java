package org.example.patientservice.repositories;

import org.example.patientservice.entities.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
}
