package com.openclassrooms.SafetyNetAlerts.controller.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonRequest {

    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phone;
    private MedicalRecord medicalHistory;
    private String city;
    private int zip;

}
