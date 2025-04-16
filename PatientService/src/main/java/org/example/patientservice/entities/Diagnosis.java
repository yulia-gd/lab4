package org.example.patientservice.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonIgnore
    private Patient patient;

    @Column(nullable = false)
    private Long doctorId;

    @NotBlank(message = "Diagnosis must have a description")
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "date_diagnosed", nullable = false)
    private LocalDate dateDiagnosed;
}
