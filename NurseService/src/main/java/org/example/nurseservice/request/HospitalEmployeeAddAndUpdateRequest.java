package org.example.nurseservice.request;


import lombok.Data;

@Data
public class HospitalEmployeeAddAndUpdateRequest {
    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    public HospitalEmployeeAddAndUpdateRequest(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
}
