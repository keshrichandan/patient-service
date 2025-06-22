package com.pm.patient_service.service;

import com.pm.patient_service.dto.AppointmentRequest;
import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.entity.appointmentEntity.Appointment;

import java.util.List;

public interface PatientServiceInterface {

     List<PatientResponseDTO> getPatients();
     PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO);
     PatientResponseDTO updatePatient(Long id, PatientRequestDTO patentRequestDTO);
     boolean deletePatient(Long id);
     Appointment bookAppointment(AppointmentRequest appointmentRequest);
     List<Appointment> getAllAppointments();
}
