package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.controller.requests.MedicalRecordRequest;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.SafetyNetAlerts.service.MedicalRecordServices;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Medical record controller.
 */
@RestController
public class MedicalRecordController {

    /**
     * The Medical record services.
     */
    final MedicalRecordServices medicalRecordServices;

    /**
     * Instantiates a new Medical record controller.
     *
     * @param medicalRecordServices the medical record services
     */
    public MedicalRecordController(MedicalRecordServices medicalRecordServices) {
        this.medicalRecordServices = medicalRecordServices;
    }

    /**
     * Medical record post string.
     *
     * @param medicalRecord the medical record
     * @return the string
     */
    @PostMapping("/medicalRecord")
    public String medicalRecordPost(@RequestBody MedicalRecordRequest medicalRecord) {
        medicalRecordServices.addAmedicalRecord(medicalRecord);
        return "medicalRecord added";
    }

    /**
     * Medical record delete string.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the string
     */
    @DeleteMapping("/medicalRecord")
    public String medicalRecordDelete(@RequestParam String firstName, @RequestParam String lastName) {
        medicalRecordServices.deleteAmedicalRecord(firstName,lastName);
        return firstName + " " + lastName + " deleted";
    }

    /**
     * Medical record put string.
     *
     * @param medicalRecord the medical record
     * @return the string
     */
    @PutMapping("/medicalRecord")
    public String medicalRecordPut(@RequestBody MedicalRecordRequest medicalRecordRequest) {
        ModelMapper modelMapper = new ModelMapper();
        MedicalRecord medicalRecord = modelMapper.map(medicalRecordRequest,MedicalRecord.class);
        medicalRecordServices.updateAmedicalRecord(medicalRecord);
        return medicalRecord.getFirstName() + " " + medicalRecord.getLastName() + " updated";
    }

}
