package com.openclassrooms.SafetyNetAlerts.service;

import com.google.gson.Gson;
import com.openclassrooms.SafetyNetAlerts.dto.AddressDTO;
import com.openclassrooms.SafetyNetAlerts.dto.PersonDTOPersonInfo;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * The type Person services.
 */
@Service
public class PersonServices {

    private final IDataServices dataServices;

    /**
     * Instantiates a new Person services.
     *
     * @param dataServices the data services
     */
    public PersonServices(DataServices dataServices) {
        this.dataServices = dataServices;
    }

    /**
     * Find person by address hash map.
     *
     * @param address the address
     * @return the hash map
     */
    public HashMap<String,List<Person>> findPersonByAddress(String address){
        //List<JSONObject> listPersons = dataServices.getListPersons();
        return dataServices.findPersonByAddress(address);
        //return dataServices.getListPersons2().stream().filter(item -> item.getAddress().toUpperCase().equals(address.toUpperCase())).collect(Collectors.toList());
    }

    /**
     * Find person by fire station hash map.
     *
     * @param fireStation the fire station
     * @return the hash map
     */
    public HashMap findPersonByFireStation(int fireStation){
        return dataServices.listPersonOfAFireStation(fireStation);
    }

    /**
     * Find phone by fire station list.
     *
     * @param fireStation the fire station
     * @return the list
     */
    public List<String> findPhoneByFireStation(int fireStation){
        return dataServices.listPhoneOfAFireStation(fireStation);
    }

    /**
     * Find email by city list.
     *
     * @param city the city
     * @return the list
     */
    public List<String> findEmailByCity(String city){
        return dataServices.listEmailOfACity(city);
    }

    /**
     * Find a person.
     *
     * @param firstName the first name
     * @param lastName  the last name
     */
    public void findAPerson(String firstName,String lastName){
        dataServices.deletePerson(firstName,lastName);
    }

    /**
     * Add a person.
     *
     * @param person the person
     */
    public void addAPerson(Person person){
        dataServices.addPerson(person);
    }

    /**
     * Update a person.
     *
     * @param person the person
     */
    public void updateAPerson(Person person){
        dataServices.updatePerson(person);
    }

    /**
     * Kids of a house hash map.
     *
     * @param address the address
     * @return the hash map
     */
    public HashMap<String,List<Person>> kidsOfAHouse(String address){
        return dataServices.kidsOfAHouse(address);
    }

    /**
     * A person list.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the list
     */
    public List<PersonDTOPersonInfo> aPerson(String firstName, String lastName) {
        return dataServices.aPerson(firstName,lastName);
    }

    /**
     * Persons from fire stations list.
     *
     * @param listStations the list stations
     * @return the list
     */
    public List<AddressDTO> personsFromFireStations(List listStations) {
        return  dataServices.personsFromFireStations(listStations);
    }
}
