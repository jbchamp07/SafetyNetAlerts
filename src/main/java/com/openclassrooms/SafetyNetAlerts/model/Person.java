package com.openclassrooms.SafetyNetAlerts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The type Person.
 */
public class Person {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phone;
    private MedicalRecord medicalHistory;
    private String city;
    @JsonIgnore
    private int zip;

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets zip.
     *
     * @return the zip
     */
    public int getZip() {
        return zip;
    }

    /**
     * Sets zip.
     *
     * @param zip the zip
     */
    public void setZip(int zip) {
        this.zip = zip;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

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
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phone;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phone = phoneNumber;
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
