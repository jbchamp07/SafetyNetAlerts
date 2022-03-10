package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.controller.requests.ListStationsRequest;
import com.openclassrooms.SafetyNetAlerts.dto.AddressDTO;
import com.openclassrooms.SafetyNetAlerts.dto.PersonDTOPersonInfo;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.service.DataServices;
import com.openclassrooms.SafetyNetAlerts.service.FireStationServices;
import com.openclassrooms.SafetyNetAlerts.service.MedicalRecordServices;
import com.openclassrooms.SafetyNetAlerts.service.PersonServices;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * The type Main controller.
 */
@RestController
public class MainController {

    /**
     * The Data services.
     */
    final DataServices dataServices;
    /**
     * The Person services.
     */
    final PersonServices personServices;
    /**
     * The Fire station services.
     */
    final FireStationServices fireStationServices;
    /**
     * The Medical record services.
     */
    final MedicalRecordServices medicalRecordServices;

    /**
     * Instantiates a new Main controller.
     *
     * @param dataServices          the data services
     * @param personServices        the person services
     * @param fireStationServices   the fire station services
     * @param medicalRecordServices the medical record services
     */
    public MainController(DataServices dataServices, PersonServices personServices, FireStationServices fireStationServices, MedicalRecordServices medicalRecordServices) {
        this.dataServices = dataServices;
        this.personServices = personServices;
        this.fireStationServices = fireStationServices;
        this.medicalRecordServices = medicalRecordServices;
    }


    /**
     * Firestation hash map.
     *
     * @param stationNumber the station number
     * @return the hash map
     */
//return list person who has stationNumber1
    @GetMapping("/firestation")
    public HashMap firestation(@RequestParam(name="stationNumber", required=false, defaultValue="None") int stationNumber) {
        return personServices.findPersonByFireStation(stationNumber);
    }


    /**
     * Child alert hash map.
     *
     * @param address the address
     * @return the hash map
     */
//return list kids(18 or less) of an address
    @GetMapping("/childAlert")
    public HashMap<String,List<Person>> childAlert(@RequestParam(name="address", required = false, defaultValue = "None")String address) {
        return personServices.kidsOfAHouse(address);
    }

    /**
     * Phone alert list.
     *
     * @param firestationNumber the firestation number
     * @return the list
     */
//return list phone of firestationNumber
    @GetMapping("/phoneAlert")
    public List<String> phoneAlert(@RequestParam(name="firestation", required = false, defaultValue = "None")int firestationNumber) {
        return personServices.findPhoneByFireStation(firestationNumber);
    }

    /**
     * Fire hash map.
     *
     * @param address the address
     * @return the hash map
     */
//return list person of an address
    @GetMapping("/fire")
    public HashMap fire(@RequestParam(name="address", required = false, defaultValue = "None")String address) {
        return personServices.findPersonByAddress(address);
    }

    /**
     * List stations list.
     *
     * @param listStations the list stations
     * @return the list
     */
//return list houses classed by address
    @GetMapping("/flood/stations")
    public List<AddressDTO> listStations(@RequestBody ListStationsRequest listStations) {
        return personServices.personsFromFireStations(listStations.getList());
    }

    /**
     * Person info list.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the list
     */
//return person informations
    @GetMapping("/personInfo")
    public List<PersonDTOPersonInfo> personInfo(@RequestParam String firstName, @RequestParam String lastName) {
        return  personServices.aPerson(firstName,lastName);
        //return personServices.allPersons();
    }

    /**
     * List stations list.
     *
     * @param city  the city
     * @param model the model
     * @return the list
     */
//return list of all email of a city
    @GetMapping("/communityEmail")
    public List<String> listStations(@RequestParam(name="city", required = false, defaultValue = "None")String city, Model model) {
        return personServices.findEmailByCity(city);
    }

}
