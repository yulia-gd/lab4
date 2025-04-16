package org.example.employeeservice.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class HospitalEmployeeUpdateRequest {

    private String firstName;

    private String lastName;

    @Email(message = "Email must be valid")
    private String email;

    @Size(min = 10, message = "Phone number must have at least 10 digits")
    private String phone;
}