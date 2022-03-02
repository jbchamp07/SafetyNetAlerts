package com.openclassrooms.SafetyNetAlerts.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;

public class PersonDTOStations {

    private String lastName;
    private String phone;
    private int age;
    private MedicalRecord medicalHistory;
    @JsonIgnore
    private String address;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public MedicalRecord getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(MedicalRecord medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
