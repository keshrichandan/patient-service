package com.pm.patient_service.entity.appointmentEntity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "appointment")
@Data
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String patientId;
    private LocalDate appointmentDate;
    private String patientName;
    private String doctorName;
    private String diseases;
    private String doctorId;
}
