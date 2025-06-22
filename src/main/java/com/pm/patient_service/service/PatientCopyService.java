package com.pm.patient_service.service;
import com.github.javafaker.Faker;
import com.pm.patient_service.entity.patientEntity.Patient;
import com.pm.patient_service.mysqlRepository.AppointmentRepository;
import com.pm.patient_service.repository.PatientRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Locale;


@Service
public class PatientCopyService {


    @Autowired
    private PatientRepository h2Repo;

    @PostConstruct
    public void insertPatients() {

            Faker faker = new Faker(new Locale("en-IND"));
        for(int i=0;i<=10;i++){

            Patient patient = new Patient();
            patient.setDateOfBirth(LocalDate.now().minusYears(i));
            patient.setAddress(faker.address().fullAddress());
            patient.setEmail(faker.internet().emailAddress());
            patient.setName(faker.name().fullName());
            patient.setRegisteredDate(LocalDate.now().minusDays(i));
            Patient savedRecord = h2Repo.save(patient);
            System.out.println("record saved in h2 db during initialization of code = " + savedRecord);
        }
    }
}
