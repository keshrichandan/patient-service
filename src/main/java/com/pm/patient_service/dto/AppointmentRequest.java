package com.pm.patient_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentRequest {

    private String patientId;
    private LocalDate appointmentDate;
    //private String doctorName;
    private Disease diseases;
    //private String doctorId;
}
