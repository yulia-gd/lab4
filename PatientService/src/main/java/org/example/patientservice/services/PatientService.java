package org.example.patientservice.services;

import jakarta.transaction.Transactional;
import org.example.patientservice.entities.Patient;
import org.example.patientservice.request.PatientAddRequest;
import org.example.patientservice.request.PatientUpdateRequest;
import org.example.patientservice.request.PrescriptionAddRequest;

import java.util.List;

public interface PatientService {
    List<Patient> getAllPatients();


    Patient getPatientById(Long id);

    Patient createPatient(PatientAddRequest patientAddRequest);

    Patient updatePatient(Long id, PatientUpdateRequest updatedPatient);

    @Transactional
    void deletePatient(Long id);

    @Transactional
    Patient dischargePatient(Long patientId);

    @Transactional
    Patient addDiagnosis(Long patientId, Long doctorId, String description);

    @Transactional
    Patient addPrescription(Long patientId, Long doctorId, PrescriptionAddRequest prescriptionToAdd);

    @Transactional
    Patient performPrescription(Long prescriptionId, Long performedById);
}
