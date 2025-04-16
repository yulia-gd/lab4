package org.example.patientservice.services;

import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.patientservice.client.DoctorClient;
import org.example.patientservice.client.HospitalEmployeeClient;
import org.example.patientservice.client.NurseClient;
import org.example.patientservice.dto.DoctorDto;
import org.example.patientservice.dto.HospitalEmployeeDto;
import org.example.patientservice.dto.NurseDto;
import org.example.patientservice.entities.Diagnosis;
import org.example.patientservice.entities.Patient;
import org.example.patientservice.entities.Prescription;
import org.example.patientservice.enums.EmploymentStatus;
import org.example.patientservice.enums.PatientStatus;
import org.example.patientservice.enums.PrescriptionType;
import org.example.patientservice.exceptions.AlreadyFiredException;
import org.example.patientservice.exceptions.AlreadyPerformedException;
import org.example.patientservice.exceptions.PatientIsDischargedException;
import org.example.patientservice.exceptions.ResourceNotFoundException;
import org.example.patientservice.repositories.PatientRepository;
import org.example.patientservice.repositories.PrescriptionRepository;
import org.example.patientservice.request.PatientAddRequest;
import org.example.patientservice.request.PatientUpdateRequest;
import org.example.patientservice.request.PrescriptionAddRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    private final PrescriptionRepository prescriptionRepository;
    private final DoctorClient doctorClient;
    private final HospitalEmployeeClient hospitalEmployeeClient;
    private final NurseClient nurseClient;


    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }


    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
    }

    @Override
    public Patient createPatient(PatientAddRequest patientAddRequest) {
        Patient patient = new Patient();
        patient.setFirstName(patientAddRequest.getFirstName());
        patient.setLastName(patientAddRequest.getLastName());
        patient.setEmail(patientAddRequest.getEmail());
        patient.setPhone(patientAddRequest.getPhone());
        patient.setBirthDate(patientAddRequest.getBirthDate());
        patient.setPatientStatus(PatientStatus.HOSPITALIZED);
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Long id, PatientUpdateRequest updatedPatient) {
        Patient patientToUpdate = getPatientById(id);
        updateExistedPatient(patientToUpdate, updatedPatient);
        return patientRepository.save(patientToUpdate);
    }

    private void updateExistedPatient(Patient patientToUpdate, PatientUpdateRequest updatedPatient){
        if (updatedPatient.getFirstName() != null && !updatedPatient.getFirstName().isEmpty()) {
            patientToUpdate.setFirstName(updatedPatient.getFirstName());
        }
        if (updatedPatient.getLastName() != null && !updatedPatient.getLastName().isEmpty()) {
            patientToUpdate.setLastName(updatedPatient.getLastName());
        }
        if (updatedPatient.getEmail() != null && !updatedPatient.getEmail().isEmpty()) {
            patientToUpdate.setEmail(updatedPatient.getEmail());
        }
        if (updatedPatient.getPhone() != null && !updatedPatient.getPhone().isEmpty()) {
            patientToUpdate.setPhone(updatedPatient.getPhone());
        }
        if (updatedPatient.getBirthDate() != null) {
            patientToUpdate.setBirthDate(updatedPatient.getBirthDate());
        }
        if (updatedPatient.getPatientStatus() != null) {
            patientToUpdate.setPatientStatus(updatedPatient.getPatientStatus());
        }
    }


    @Override
    public void deletePatient(Long id) {
        Patient patient = getPatientById(id);
        patientRepository.delete(patient);
    }

    @Transactional
    @Override
    public Patient dischargePatient(Long patientId) {
        Patient patient = getPatientById(patientId);

        List<Diagnosis> diagnoses = patient.getDiagnoses();
        if (!diagnoses.isEmpty()) {
            Diagnosis lastDiagnosis = diagnoses.getLast();
            patient.setFinalDiagnosis(lastDiagnosis);
        } else {
            patient.setFinalDiagnosis(null);
        }

        patient.setPatientStatus(PatientStatus.DISCHARGED);

        patientRepository.save(patient);
        return patient;
    }

    @Transactional
    @Override
    public Patient addDiagnosis(Long patientId, Long doctorId, String description) {
        Patient patient = getPatientById(patientId);

        if (patient.getPatientStatus() != PatientStatus.HOSPITALIZED) {
            throw new PatientIsDischargedException("Cannot add diagnosis to a discharged patient");
        }
        DoctorDto doctor = doctorClient.getDoctorById(doctorId).getData();

        if (doctor.hospitalEmployeeDto().employmentStatus() == EmploymentStatus.FIRED) {
            throw new AlreadyFiredException("Doctor is terminated and cannot add diagnosis");
        }

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setPatient(patient);
        diagnosis.setDoctorId(doctor.id());
        diagnosis.setDescription(description);
        diagnosis.setDateDiagnosed(LocalDate.now());

        patient.getDiagnoses().add(diagnosis);

        patientRepository.save(patient);
        return patient;
    }


    @Transactional
    @Override
    public Patient addPrescription(Long patientId, Long doctorId, PrescriptionAddRequest prescriptionToAdd) {
        Patient patient =  getPatientById(patientId);

        if (patient.getPatientStatus() != PatientStatus.HOSPITALIZED) {
            throw new PatientIsDischargedException("Cannot add prescription to a discharged patient");
        }

        DoctorDto doctor = doctorClient.getDoctorById(doctorId).getData();

        if (doctor.hospitalEmployeeDto().employmentStatus() == EmploymentStatus.FIRED) {
            throw new AlreadyFiredException("Doctor is terminated and cannot add prescription");
        }

        Prescription prescription = new Prescription();
        prescription.setPatient(patient);
        prescription.setDoctorId(doctor.id());
        prescription.setDescription(prescriptionToAdd.getDescription());
        prescription.setType(prescriptionToAdd.getType());
        prescription.setDateIssued(LocalDate.now());

        patient.getPrescriptions().add(prescription);

        patientRepository.save(patient);
        return patient;
    }

    @Transactional
    public Patient performPrescription(Long prescriptionId, Long performedById) {

        Prescription prescription = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(()-> new ResourceNotFoundException("Prescription is not found"));

        if(prescription.getPerformedById()!=null){
            throw new AlreadyPerformedException("This prescription is already performed");
        }
        Patient patient = prescription.getPatient();


        if (patient.getPatientStatus() == PatientStatus.DISCHARGED) {
            throw new PatientIsDischargedException("Cannot perform prescription for a discharged patient");
        }

        HospitalEmployeeDto performedBy =  hospitalEmployeeClient.getHospitalEmployeeById(performedById).getData();


        if (performedBy.employmentStatus()== EmploymentStatus.FIRED) {
            throw new AlreadyFiredException("Employee is terminated and cannot perform prescription");
        }
        try {
            NurseDto nurseDto = nurseClient.getNurseByHospitalEmployeeId(performedBy.id()).getData();
            System.out.println(nurseDto);

            if (nurseDto != null && prescription.getType() == PrescriptionType.SURGERY) {
                throw new RuntimeException("Nurse can't do surgery");
            }

        } catch (FeignException.NotFound ex) {
            // Медсестру не знайдено – ігноруємо, бо вона просто не причетна
            System.out.println("Nurse not found – skipping nurse-specific validation");
        }


        prescription.setPerformedById(performedBy.id());

        patientRepository.save(patient);
        return patient;
    }

}
