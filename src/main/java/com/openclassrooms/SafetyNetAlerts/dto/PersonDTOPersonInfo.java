package com.openclassrooms.SafetyNetAlerts.dto;

import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;

/**
 * The type Person dto person info.
 */
public class PersonDTOPersonInfo {
    private String lastName;
    private String address;
    private int age;
    private String email;
    private MedicalRecord medicalHistory;

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
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
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

}
