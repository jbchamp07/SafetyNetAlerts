package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordServices {

    private final DataServices dataServices;

    public MedicalRecordServices(DataServices dataServices) {
        this.dataServices = dataServices;
    }

    public void addAmedicalRecord(MedicalRecord medicalRecord) {
        dataServices.addMedicalRecord(medicalRecord);
    }

    public void deleteAmedicalRecord(String firstName, String lastName) {
        dataServices.deleteMedicalRecord(firstName,lastName);
    }

    public void updateAmedicalRecord(MedicalRecord medicalRecord) {
        dataServices.updateMedicalRecord(medicalRecord);
    }
}
