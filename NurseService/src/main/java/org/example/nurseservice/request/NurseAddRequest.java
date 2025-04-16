package org.example.nurseservice.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.nurseservice.enums.NurseQualification;

@Data
public class NurseAddRequest {
    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    @NotBlank(message = "Nurse must have department")
    private String department;

    @NotNull(message = "Nurse must  have qualification")
    private NurseQualification qualification;
}
