package org.example.doctorserviice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "doctor")
@Getter
@Setter
public class Doctor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Specialization is required")
    @Column(name = "specialization")
    private String specialization;

    @Column(nullable = false)
    private Long hospitalEmployeeId;
}

