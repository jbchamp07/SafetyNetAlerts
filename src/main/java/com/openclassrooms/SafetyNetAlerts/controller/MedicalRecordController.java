package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.SafetyNetAlerts.service.MedicalRecordServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicalRecordController {

    final MedicalRecordServices medicalRecordServices;

    public MedicalRecordController(MedicalRecordServices medicalRecordServices) {
        this.medicalRecordServices = medicalRecordServices;
    }

    @PostMapping("/medicalRecord")
    public String medicalRecordPost(MedicalRecord medicalRecord) {
        medicalRecordServices.addAmedicalRecord(medicalRecord);
        return "medicalRecord added";
    }
    @DeleteMapping("/medicalRecord")
    public String medicalRecordDelete(@RequestParam String firstName, @RequestParam String lastName) {
        medicalRecordServices.deleteAmedicalRecord(firstName,lastName);
        return firstName + " " + lastName + " deleted";
    }
    @PutMapping("/medicalRecord")
    public String medicalRecordPut(MedicalRecord medicalRecord) {
        medicalRecordServices.updateAmedicalRecord(medicalRecord);
        return medicalRecord.getFirstName() + " " + medicalRecord.getLastName() + " updated";
    }

}
