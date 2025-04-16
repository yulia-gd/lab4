package org.example.nurseservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.example.nurseservice.enums.NurseQualification;

@Entity
@Table(name = "nurse")
@Getter
@Setter
public class Nurse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long hospitalEmployeeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "qualification")
    @NotNull
    private NurseQualification qualification;

    @Column(name = "department")
    @NotBlank(message = "Nurse must have department")
    private String department;
}