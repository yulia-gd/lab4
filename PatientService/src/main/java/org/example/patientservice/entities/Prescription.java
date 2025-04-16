package org.example.patientservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.patientservice.enums.PrescriptionType;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonIgnore
    private Patient patient;

    @Column( nullable = false)
    private Long doctorId;


    @Column()
    private Long performedById;

    @Column(name = "description")
    @NotBlank(message = "Prescription must have description")
    private String description;

    @Column(name = "date_issued", nullable = false)
    private LocalDate dateIssued;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @NotNull(message = "Prescription must have type")
    private PrescriptionType type;
}
