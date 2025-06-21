package com.pm.patient_service.service;

import com.pm.patient_service.dto.AppointmentRequest;
import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.entity.appointmentEntity.Appointment;
import com.pm.patient_service.exception.EmailAlreadyExistException;
import com.pm.patient_service.exception.PatientNotFoundException;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.entity.patientEntity.Patient;
import com.pm.patient_service.mysqlRepository.AppointmentRepository;
import com.pm.patient_service.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    public PatientService(PatientRepository patientRepository, AppointmentRepository appointmentRepository) {
        this.patientRepository = patientRepository;
        this.appointmentRepository =appointmentRepository;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {

        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistException("Email already exist! "+patientRequestDTO.getEmail());
        }
        Patient patient = PatientMapper.toModel(patientRequestDTO);
        log.info("getting patient info to be create for: {}", patient.getName());
        Patient savedPatient = patientRepository.save(patient);
        return PatientMapper.toDTO(savedPatient);
    }

    public PatientResponseDTO updatePatient(Long id, PatientRequestDTO patentRequestDTO){
        Patient existingPatient = patientRepository.findById(id).
                orElseThrow(() -> new PatientNotFoundException("Patient Not found with id: " + id));

        if(!patientRepository.existsByEmail(patentRequestDTO.getEmail())){
            throw new EmailAlreadyExistException("Email not exist! "+patentRequestDTO.getEmail());
        }

        existingPatient.setRegisteredDate(LocalDate.parse(patentRequestDTO.getRegisteredDate()));
        existingPatient.setAddress(patentRequestDTO.getAddress());
        existingPatient.setEmail(patentRequestDTO.getEmail());
        existingPatient.setName(patentRequestDTO.getName());

        if(LocalDate.parse(patentRequestDTO.getDateOfBirth()).isBefore(LocalDate.now())) {
            existingPatient.setDateOfBirth(LocalDate.parse(patentRequestDTO.getDateOfBirth()));
        }
        log.info("existing patient to be replace :{}",existingPatient);
        patientRepository.save(existingPatient);
        log.info("Record updated successfully :{}",existingPatient);

        return PatientMapper.toDTO(existingPatient);
    }

    public boolean deletePatient(Long id){

         if(!patientRepository.existsById(id)){
             return false;
         }
         patientRepository.deleteById(id);
         return true;
    }

    public Appointment bookAppointment(AppointmentRequest appointmentRequest){
        Patient existingPatient = patientRepository.findById(Long.valueOf(appointmentRequest.getPatientId())).
                orElseThrow(() -> new PatientNotFoundException(
                "Patient Not found with id: " + appointmentRequest.getPatientId() + ". Please register."));

        Appointment aptRequest = PatientMapper.mapToAppointment(appointmentRequest);
        aptRequest.setPatientName(existingPatient.getName());

        if(!appointmentRepository.findByPatientId(appointmentRequest.getPatientId()).isEmpty()){
            return null;
        }
        System.out.println("appointment result going to be saved: " + aptRequest);
        return appointmentRepository.save(aptRequest);
    }
}