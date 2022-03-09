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

@RestController
public class MainController {

    final DataServices dataServices;
    final PersonServices personServices;
    final FireStationServices fireStationServices;
    final MedicalRecordServices medicalRecordServices;

    public MainController(DataServices dataServices, PersonServices personServices, FireStationServices fireStationServices, MedicalRecordServices medicalRecordServices) {
        this.dataServices = dataServices;
        this.personServices = personServices;
        this.fireStationServices = fireStationServices;
        this.medicalRecordServices = medicalRecordServices;
    }


    //return list person who has stationNumber1
    @GetMapping("/firestation")
    public HashMap firestation(@RequestParam(name="stationNumber", required=false, defaultValue="None") int stationNumber) {
        return personServices.findPersonByFireStation(stationNumber);
    }




    //return list kids(18 or less) of an address
    @GetMapping("/childAlert")
    public HashMap<String,List<Person>> childAlert(@RequestParam(name="address", required = false, defaultValue = "None")String address) {
        return personServices.kidsOfAHouse(address);
    }

    //return list phone of firestationNumber
    @GetMapping("/phoneAlert")
    public List<String> phoneAlert(@RequestParam(name="firestation", required = false, defaultValue = "None")int firestationNumber) {
        return personServices.findPhoneByFireStation(firestationNumber);
    }

    //return list person of an address
    @GetMapping("/fire")
    public HashMap fire(@RequestParam(name="address", required = false, defaultValue = "None")String address) {
        return personServices.findPersonByAddress(address);
    }

    //return list houses classed by address
    @GetMapping("/flood/stations")
    public List<AddressDTO> listStations(@RequestBody ListStationsRequest listStations) {
        return personServices.personsFromFireStations(listStations.getList());
    }

    //return person informations
    @GetMapping("/personInfo")
    public List<PersonDTOPersonInfo> personInfo(@RequestParam String firstName, @RequestParam String lastName) {
        return  personServices.aPerson(firstName,lastName);
        //return personServices.allPersons();
    }

    //return list of all email of a city
    @GetMapping("/communityEmail")
    public List<String> listStations(@RequestParam(name="city", required = false, defaultValue = "None")String city, Model model) {
        return personServices.findEmailByCity(city);
    }

}
