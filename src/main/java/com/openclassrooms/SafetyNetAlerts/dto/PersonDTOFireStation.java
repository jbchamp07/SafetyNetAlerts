package com.openclassrooms.SafetyNetAlerts.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;

public class PersonDTOFireStation {

    private String firstName;
    private String lastName;
    private String address;
    @JsonIgnore
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
