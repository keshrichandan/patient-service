package com.pm.patient_service.controller;

import com.pm.patient_service.dto.AppointmentRequest;
import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.entity.appointmentEntity.Appointment;
import com.pm.patient_service.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.model.internal.XMLContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/patients")

//http://localhost:4000/v3/api-docs
/**
 * to use swagger API first we go to v3/api-docs api to generate json and then after go to the swagger editor
 * to past the copied json from Swagger api-docs.
 * http://localhost:4000/swagger-ui/index.html#/Patient/updatePatient
 */
@Tag(name="Patient",description="An API for managing Patients")
public class PatientController {

    private final PatientService patientService;
    public PatientController(PatientService service) {
        this.patientService = service;
    }

    //http://localhost:4000/patients/
    @GetMapping("/")
    @Operation(description = "To get all patients")
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        List<PatientResponseDTO> patients = patientService.getPatients();
        log.info("getting patients size: {}", patients.size());
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }


    //http://localhost:4000/patients/addPatient
    @PostMapping("/addPatient")
    @Operation(description = "To create a new Patient")
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO savedPatient = patientService.createPatient(patientRequestDTO);
        return new ResponseEntity<PatientResponseDTO>(savedPatient, HttpStatus.CREATED);
    }
    //http://localhost:4000/patients/update/{id}
    @PutMapping("/update/{id}")
    @Operation(description = "To update patient record by Id")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable Long id, @RequestBody PatientRequestDTO patientRequestDTO){
        log.info("getting request for UUID :{}",id);
        PatientResponseDTO updatedPatientRecord = patientService.updatePatient(id, patientRequestDTO);
        log.info("Record updated successfully for id: {}, {}", id, updatedPatientRecord);
        return new ResponseEntity<>(updatedPatientRecord,HttpStatus.OK);
    }

    //http://localhost:4000/patients/update
    @PutMapping("/update")
    @Operation(description = "To update patient record by requestHeader")
    public ResponseEntity<PatientResponseDTO> updatePatientByHeader(@RequestHeader(required = true) Long id,
                                                                    @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO){
        log.info("getting request for UUID from header :{}",id);
        PatientResponseDTO updatedPatientRecord = patientService.updatePatient(id, patientRequestDTO);
        log.info("Record updated successfully for uuid: {}, {}", id, updatedPatientRecord);
        return new ResponseEntity<>(updatedPatientRecord,HttpStatus.OK);
    }

    //http://localhost:4000/patients/removePatientById?{id}
    @DeleteMapping("/removePatientById")
    @Operation(description="To remove patient")
    public ResponseEntity<?> deletePatientById(@RequestParam Long id){
        log.info("getting request to remove patient for the Id: {}",id);
        boolean isDeleted = patientService.deletePatient(id);
        if(!isDeleted){
            log.info("patient not found with ID:{}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Patient not found with ID:" + id);
        }
            log.info("record deleted successfully: {}",id);
        return ResponseEntity.ok("Patient removed successfully!");

    }
    //http://localhost:4000/patients/bookAppointment
    @PostMapping("/bookAppointment")
    @Operation(description="To create patient appointments")
    public ResponseEntity<Appointment> bookAppointments(@RequestBody AppointmentRequest appointmentRequest){
        Appointment savedAppointment = patientService.bookAppointment(appointmentRequest);
        return new ResponseEntity<>(savedAppointment, HttpStatus.OK);
    }
}
