package com.pm.patient_service.dto;

import lombok.Getter;

@Getter
public enum Disease {
    FLU("Dr. Smith", "DOC001"),
    DIABETES("Dr. Patel", "DOC002"),
    HYPERTENSION("Dr. Chen", "DOC003"),
    ASTHMA("Dr. Kumar", "DOC004"),
    COVID_19("Dr. Sharma", "DOC005"),
    MALARIA("Dr. Ahmed", "DOC006"),
    OTHER("Dr. General", "DOC000");

    private final String doctorName;
    private final String doctorId;

    Disease(String doctorName, String doctorId) {
        this.doctorName = doctorName;
        this.doctorId = doctorId;
    }

}

