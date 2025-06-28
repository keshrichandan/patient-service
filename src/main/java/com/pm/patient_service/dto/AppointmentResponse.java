package com.pm.patient_service.dto;

import com.pm.patient_service.entity.appointmentEntity.Appointment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AppointmentResponse {
    private String message;
    private Appointment appointment;
}
