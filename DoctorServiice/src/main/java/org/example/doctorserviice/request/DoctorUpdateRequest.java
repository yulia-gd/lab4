package org.example.doctorserviice.request;

import lombok.Data;

@Data
public class DoctorUpdateRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String specialization;
}