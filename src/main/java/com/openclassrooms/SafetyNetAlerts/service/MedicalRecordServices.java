package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.controller.requests.MedicalRecordRequest;
import com.openclassrooms.SafetyNetAlerts.dto.PersonDTOPersonInfo;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Medical record services.
 */
@Service
public class MedicalRecordServices {

    private final IDataServices dataServices;

    /**
     * Instantiates a new Medical record services.
     *
     * @param dataServices the data services
     */
    public MedicalRecordServices(DataServices dataServices) {
        this.dataServices = dataServices;
    }

    /**
     * Add amedical record.
     *
     * @param medicalRecordRequest the medical record request
     */
    public void addAmedicalRecord(MedicalRecordRequest medicalRecordRequest) {
        ModelMapper modelMapper = new ModelMapper();
        MedicalRecord medicalRecord = modelMapper.map(medicalRecordRequest,MedicalRecord.class);
        dataServices.addMedicalRecord(medicalRecord);
    }

    /**
     * Delete amedical record.
     *
     * @param firstName the first name
     * @param lastName  the last name
     */
    public void deleteAmedicalRecord(String firstName, String lastName) {
        dataServices.deleteMedicalRecord(firstName,lastName);
    }

    /**
     * Update amedical record.
     *
     * @param medicalRecord the medical record
     */
    public void updateAmedicalRecord(MedicalRecord medicalRecord) {
        dataServices.updateMedicalRecord(medicalRecord);
    }
}
