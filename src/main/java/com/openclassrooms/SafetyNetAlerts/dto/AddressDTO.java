package com.openclassrooms.SafetyNetAlerts.dto;

import com.openclassrooms.SafetyNetAlerts.model.Person;

import java.util.List;

/**
 * The type Address dto.
 */
public class AddressDTO {
    private String address;
    private List<PersonDTOStations> persons;

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
     * Gets persons.
     *
     * @return the persons
     */
    public List<PersonDTOStations> getPersons() {
        return persons;
    }

    /**
     * Sets persons.
     *
     * @param persons the persons
     */
    public void setPersons(List<PersonDTOStations> persons) {
        this.persons = persons;
    }
}
