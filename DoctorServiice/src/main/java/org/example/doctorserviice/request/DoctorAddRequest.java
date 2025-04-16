package org.example.doctorserviice.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DoctorAddRequest {
    private String firstName;

    private String lastName;

    private String email;

    private String phone;
    @NotBlank(message = "Specialization can`t be empty")
    private String specialization;
}
