package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.controller.requests.MedicalRecordRequest;
import com.openclassrooms.SafetyNetAlerts.dto.PersonDTOPersonInfo;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordServices {

    private final IDataServices dataServices;

    public MedicalRecordServices(DataServices dataServices) {
        this.dataServices = dataServices;
    }

    public void addAmedicalRecord(MedicalRecordRequest medicalRecordRequest) {
        ModelMapper modelMapper = new ModelMapper();
        MedicalRecord medicalRecord = modelMapper.map(medicalRecordRequest,MedicalRecord.class);
        dataServices.addMedicalRecord(medicalRecord);
    }

    public void deleteAmedicalRecord(String firstName, String lastName) {
        dataServices.deleteMedicalRecord(firstName,lastName);
    }

    public void updateAmedicalRecord(MedicalRecord medicalRecord) {
        dataServices.updateMedicalRecord(medicalRecord);
    }
}
