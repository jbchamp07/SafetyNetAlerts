package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.service.MedicalRecordServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

public class MedicalRecordController {

    final MedicalRecordServices medicalRecordServices;

    public MedicalRecordController(MedicalRecordServices medicalRecordServices) {
        this.medicalRecordServices = medicalRecordServices;
    }

    @PostMapping("/medicalRecord/{firstName}/{lastName}/{birthDate}/{medications}/{allergies}")
    public String medicalRecordPost(@PathVariable String firstName, @PathVariable String lastName, @PathVariable String birthDate, @PathVariable List<String> medications, @PathVariable List<String> allergies) {
        medicalRecordServices.addAmedicalRecord(firstName,lastName,birthDate,medications,allergies);
        return "medicalRecord added";
    }
    @DeleteMapping("/medicalRecord/{firstName}/{lastName}")
    public String medicalRecordDelete(@PathVariable String firstName, @PathVariable String lastName) {
        medicalRecordServices.deleteAmedicalRecord(firstName,lastName);
        return firstName + " " + lastName + " deleted";
    }
    @PutMapping("/medicalRecord/{firstName}/{lastName}/{birthDate}/{medications}/{allergies}")
    public String medicalRecordPut(@PathVariable String firstName,@PathVariable String lastName,@PathVariable String birthDate,@PathVariable List<String> medications,@PathVariable List<String> allergies) {
        medicalRecordServices.updateAmedicalRecord(firstName,lastName,birthDate,medications,allergies);
        return firstName + " " + lastName + " updated";
    }

}
