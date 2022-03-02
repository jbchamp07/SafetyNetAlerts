package com.openclassrooms.SafetyNetAlerts.dto;

import com.openclassrooms.SafetyNetAlerts.model.Person;

import java.util.List;

public class AddressDTO {
    private String address;
    private List<PersonDTOStations> persons;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<PersonDTOStations> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonDTOStations> persons) {
        this.persons = persons;
    }
}
