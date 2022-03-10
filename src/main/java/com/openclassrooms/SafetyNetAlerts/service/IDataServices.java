package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.dto.AddressDTO;
import com.openclassrooms.SafetyNetAlerts.dto.PersonDTOPersonInfo;
import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * The interface Data services.
 */
public interface IDataServices {


    /**
     * Gets list persons 2.
     *
     * @return the list persons 2
     */
    List<Person> getListPersons2();

    /**
     * Gets list fire stations 2.
     *
     * @return the list fire stations 2
     */
    List<FireStation> getListFireStations2();

    /**
     * Gets list medical records 2.
     *
     * @return the list medical records 2
     */
    List<MedicalRecord> getListMedicalRecords2();

    /**
     * List person of a fire station hash map.
     *
     * @param stationNumber the station number
     * @return the hash map
     */
    HashMap listPersonOfAFireStation(int stationNumber);

    /**
     * List phone of a fire station list.
     *
     * @param stationNumber the station number
     * @return the list
     */
    List<String> listPhoneOfAFireStation(int stationNumber);

    /**
     * List email of a city list.
     *
     * @param city the city
     * @return the list
     */
    List<String> listEmailOfACity(String city);

    /**
     * Add person.
     *
     * @param person the person
     */
    void addPerson(Person person);

    /**
     * Delete person.
     *
     * @param firstName the first name
     * @param lastName  the last name
     */
    void deletePerson(String firstName, String lastName);

    /**
     * Update person.
     *
     * @param person the person
     */
    void updatePerson(Person person);

    /**
     * Add fire station.
     *
     * @param fireStation the fire station
     */
    void addFireStation(FireStation fireStation);

    /**
     * Delete fire station.
     *
     * @param fireStation the fire station
     */
    void deleteFireStation(FireStation fireStation);

    /**
     * Update fire station.
     *
     * @param fireStation the fire station
     */
    void updateFireStation(FireStation fireStation);

    /**
     * Add medical record.
     *
     * @param medicalRecord the medical record
     */
    void addMedicalRecord(MedicalRecord medicalRecord);

    /**
     * Delete medical record.
     *
     * @param firstName the first name
     * @param lastName  the last name
     */
    void deleteMedicalRecord(String firstName, String lastName);

    /**
     * Update medical record.
     *
     * @param medicalRecord the medical record
     */
    void updateMedicalRecord(MedicalRecord medicalRecord);

    /**
     * Kids of a house hash map.
     *
     * @param address the address
     * @return the hash map
     */
    HashMap<String,List<Person>> kidsOfAHouse(String address);

    /**
     * A person list.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the list
     */
    List<PersonDTOPersonInfo> aPerson(String firstName, String lastName);

    /**
     * Persons from fire stations list.
     *
     * @param listStations the list stations
     * @return the list
     */
    List<AddressDTO> personsFromFireStations(List listStations);

    /**
     * Find person by address hash map.
     *
     * @param address the address
     * @return the hash map
     */
    HashMap<String,List<Person>> findPersonByAddress(String address);

}
