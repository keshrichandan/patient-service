package com.pm.patient_service.mapper;

import com.pm.patient_service.dto.AppointmentRequest;
import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.entity.appointmentEntity.Appointment;
import com.pm.patient_service.entity.patientEntity.Patient;

import java.time.LocalDate;

public class PatientMapper {

    public static PatientResponseDTO toDTO(Patient patient){
        PatientResponseDTO patientResponseDto = new PatientResponseDTO();
        patientResponseDto.setId(String.valueOf(patient.getId().toString()));
        patientResponseDto.setName(patient.getName());
        patientResponseDto.setAddress(patient.getAddress());
        patientResponseDto.setEmail(patient.getEmail());
        patientResponseDto.setDateOfBirth(patient.getDateOfBirth().toString());
        return patientResponseDto;
    }

    public static Patient toModel(PatientRequestDTO patientRequestDTO){
        Patient patient = new Patient();
        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
        
        return patient;
    }

    public static Appointment mapToAppointment(AppointmentRequest appointmentRequest){
        Appointment appointment = new Appointment();
        appointment.setPatientId(appointmentRequest.getPatientId());
        appointment.setAppointmentDate(appointmentRequest.getAppointmentDate().plusDays(3));
        appointment.setDiseases(appointmentRequest.getDiseases().name());
        appointment.setDoctorId(appointmentRequest.getDiseases().getDoctorId());
        appointment.setDoctorName(appointmentRequest.getDiseases().getDoctorName());

        return appointment;
    }
}
