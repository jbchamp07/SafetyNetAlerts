package com.openclassrooms.SafetyNetAlerts.dto;

import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;

public class PersonDTOFire {

    private String lastName;
    private String phone;
    private int age;
    private MedicalRecord medicalHistory;

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
}
