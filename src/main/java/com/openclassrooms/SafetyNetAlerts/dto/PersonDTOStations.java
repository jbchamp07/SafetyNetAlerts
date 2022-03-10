package com.openclassrooms.SafetyNetAlerts.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;

/**
 * The type Person dto stations.
 */
public class PersonDTOStations {

    private String lastName;
    private String phone;
    private int age;
    private MedicalRecord medicalHistory;
    @JsonIgnore
    private String address;

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets age.
     *
     * @param age the age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets medical history.
     *
     * @return the medical history
     */
    public MedicalRecord getMedicalHistory() {
        return medicalHistory;
    }

    /**
     * Sets medical history.
     *
     * @param medicalHistory the medical history
     */
    public void setMedicalHistory(MedicalRecord medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

}
