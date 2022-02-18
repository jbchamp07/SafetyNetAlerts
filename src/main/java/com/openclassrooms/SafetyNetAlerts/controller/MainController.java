package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.service.DataServices;
import com.openclassrooms.SafetyNetAlerts.service.PersonServices;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    final DataServices dataServices;
    final PersonServices personServices;

    public MainController(DataServices dataServices, PersonServices personServices) {
        this.dataServices = dataServices;
        this.personServices = personServices;
    }

    //CRUD person
    @GetMapping("/person")
    public String person() {
        return "person";
    }
    @PostMapping("/person")
    public String personPost(@RequestParam(name="stationNumber", required=false, defaultValue="None") int stationNumber) {
        return "person";
    }
    @DeleteMapping("/person")
    public String personDelete(@RequestParam(name="stationNumber", required=false, defaultValue="None") int stationNumber) {
        return "person";
    }

    //CRUD fireStation
    //return list person who has stationNumber1
    @GetMapping("/firestation")
    public List<Person> firestation(@RequestParam(name="stationNumber", required=false, defaultValue="None") int stationNumber, Model model) {
        return personServices.findPersonByFireStation(stationNumber);
    }

    //CRUD medicalRecord
    @GetMapping("/medicalRecord")
    public String medicalRecord() {
        return "medicalRecord";
    }

    //return list kids(18 or less) of an address
    @GetMapping("/childAlert")
    public String childAlert(@RequestParam(name="address", required = false, defaultValue = "None")String address, Model model) {
        model.addAttribute("address", address);
        return "childAlert";
    }

    //return list phone of firestationNumber
    @GetMapping("/phoneAlert")
    public List<String> phoneAlert(@RequestParam(name="firestation", required = false, defaultValue = "None")int firestationNumber, Model model) {

        return personServices.findPhoneByFireStation(firestationNumber);
    }

    //return list person of an address
    @GetMapping("/fire")
    public List<Person> fire(@RequestParam(name="address", required = false, defaultValue = "None")String address, Model model) {
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
