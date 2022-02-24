package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.service.DataServices;
import com.openclassrooms.SafetyNetAlerts.service.FireStationServices;
import com.openclassrooms.SafetyNetAlerts.service.MedicalRecordServices;
import com.openclassrooms.SafetyNetAlerts.service.PersonServices;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    //CRUD person
    @GetMapping("/person")
    public String person() {
        return "person";
    }


    //CRUD fireStation
    //return list person who has stationNumber1
    @GetMapping("/firestation")
    public List<Person> firestation(@RequestParam(name="stationNumber", required=false, defaultValue="None") int stationNumber) {
        return personServices.findPersonByFireStation(stationNumber);
    }


    //CRUD medicalRecord
    @GetMapping("/medicalRecord")
    public String medicalRecord() {
        return "medicalRecord";
    }



    //return list kids(18 or less) of an address
    @GetMapping("/childAlert")
    public String childAlert(@RequestParam(name="address", required = false, defaultValue = "None")String address) {
        return "childAlert";
    }

    //return list phone of firestationNumber
    @GetMapping("/phoneAlert")
    public List<String> phoneAlert(@RequestParam(name="firestation", required = false, defaultValue = "None")int firestationNumber) {
        return personServices.findPhoneByFireStation(firestationNumber);
    }

    //return list person of an address
    @GetMapping("/fire")
    public List<Person> fire(@RequestParam(name="address", required = false, defaultValue = "None")String address) {
        return personServices.findPersonByAddress(address);
    }

    //return list houses classed by address
    @GetMapping("/flood/stations")
    public String listStations(@RequestParam(name="listStations", required = false, defaultValue = "None")List listStations, Model model) {
        model.addAttribute("listStations", listStations);
        return "stations";
    }

    //return person informations
    @GetMapping("/personInfo")
    public List<Person> personInfo(@RequestParam(name="person", required = false, defaultValue = "None")String firstName,@RequestParam(name="person", required = false, defaultValue = "None")String lastName, Model model) {
        return personServices.allPersons();
    }

    //return list of all email of a city
    @GetMapping("/communityEmail")
    public List<String> listStations(@RequestParam(name="city", required = false, defaultValue = "None")String city, Model model) {
        return personServices.findEmailByCity(city);
    }

}
