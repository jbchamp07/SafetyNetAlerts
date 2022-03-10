package com.openclassrooms.SafetyNetAlerts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * The type Medical record.
 */
public class MedicalRecord {
    /**
     * The First name.
     */
    @JsonIgnore
    String firstName;
    /**
     * The Last name.
     */
    @JsonIgnore
    String lastName;
    /**
     * The Birthdate.
     */
    @JsonIgnore
    String birthdate;
    /**
     * The Medications.
     */
    List<String> medications;
    /**
     * The Allergies.
     */
    List<String> allergies;

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
     * Gets birthdate.
     *
     * @return the birthdate
     */
    public String getBirthdate() {
        return birthdate;
    }

    /**
     * Sets birthdate.
     *
     * @param birthdate the birthdate
     */
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * Gets medications.
     *
     * @return the medications
     */
    public List<String> getMedications() {
        return medications;
    }

    /**
     * Sets medications.
     *
     * @param medications the medications
     */
    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    /**
     * Gets allergies.
     *
     * @return the allergies
     */
    public List<String> getAllergies() {
        return allergies;
    }

    /**
     * Sets allergies.
     *
     * @param allergies the allergies
     */
    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }
}
