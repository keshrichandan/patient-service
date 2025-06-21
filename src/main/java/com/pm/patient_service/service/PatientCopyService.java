package com.pm.patient_service.service;
import com.pm.patient_service.entity.patientEntity.Patient;
import com.pm.patient_service.mysqlRepository.AppointmentRepository;
import com.pm.patient_service.repository.PatientRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class PatientCopyService {


    @Autowired
    private PatientRepository h2Repo;

    @PostConstruct
    public void insertPatients() {

        Patient patient = new Patient();
        //patient.setId(12345L);
        patient.setDateOfBirth(LocalDate.now().minusYears(30));
        patient.setAddress("123, new street line");
        patient.setEmail("chandan74@gmail.com");
        patient.setName("chandan");
        patient.setRegisteredDate(LocalDate.now());

        Patient savedRecord = h2Repo.save(patient);
        System.out.println("record saved in h2 db during initialization of code = " + savedRecord);
    }
}
