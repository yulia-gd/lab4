package org.example.employeeservice.repository;

import org.example.employeeservice.entities.HospitalEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalEmployeeRepository extends JpaRepository<HospitalEmployee, Long> {
}
