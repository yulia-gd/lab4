package org.example.nurseservice.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.nurseservice.enums.NurseQualification;

@Data
public class NurseUpdateRequest {
    private String firstName;

    private String lastName;

    private String email;

    private String phone;


    private String department;

    private NurseQualification qualification;
}
