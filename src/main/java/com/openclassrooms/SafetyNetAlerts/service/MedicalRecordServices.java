package com.openclassrooms.SafetyNetAlerts.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordServices {

    private final DataServices dataServices;

    public MedicalRecordServices(DataServices dataServices) {
        this.dataServices = dataServices;
    }

    public void addAmedicalRecord(String firstName, String lastName, String birthDate, List<String> medications, List<String> allergies) {
        dataServices.addMedicalRecord(firstName,lastName,birthDate,medications,allergies);
    }

    public void deleteAmedicalRecord(String firstName, String lastName) {
        dataServices.deleteMedicalRecord(firstName,lastName);
    }

    public void updateAmedicalRecord(String firstName, String lastName,String birthDate,List<String> medications,List<String> allergies) {
        dataServices.updateMedicalRecord(firstName,lastName,birthDate,medications,allergies);
    }
}
