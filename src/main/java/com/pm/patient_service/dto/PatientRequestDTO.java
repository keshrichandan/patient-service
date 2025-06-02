package com.pm.patient_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PatientRequestDTO {

    @NotBlank(message = "name is required")
    @Size(max = 100,message = "Not more than 100 char")
    private String name;

    @NotBlank(message = "email is required")
    @Email(message = "email should be valid")
    private String email;

    @NotBlank(message = "address is required")
    private String address;

    @NotBlank(message = "dateOfBirth is required")
    private String dateOfBirth;

    @NotBlank(message = "registeredDate is required")
    private String registeredDate;

    @Override
    public String toString() {
        return "PatientRequestDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", registeredDate='" + registeredDate + '\'' +
                '}';
    }
}
