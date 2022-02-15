package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    //CRUD person
    @GetMapping("/person")
    public String person() {
        return "person";
    }

    //CRUD fireStation
    //return list person who has stationNumber1
    @GetMapping("/firestation")
    public String firestation(@RequestParam(name="stationNumber", required=false, defaultValue="None") int stationNumber, Model model) {
        model.addAttribute("stationNumber", stationNumber);
        return "firestation";
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
    public String phoneAlert(@RequestParam(name="firestationNumber", required = false, defaultValue = "None")int firestationNumber, Model model) {
        model.addAttribute("firestationNumber", firestationNumber);
        return "phoneAlert";
    }

    //return list person of an address
    @GetMapping("/fire")
    public Map<String, Person> fire(@RequestParam(name="address", required = false, defaultValue = "None")String address, Model model) {
        //model.addAttribute("address", address);
        Map<String, Person> listPerson = new HashMap<String, Person>();
        Person person1 = new Person();
        person1.setFirstName("Jean-Baptiste");
        person1.setLastName("Champetier");
        person1.setAddress("rue 123");
        person1.setCity("St andré de cruizères");
        person1.setEmail("jb.champetier@gmail.com");
        person1.setPhoneNumber("0610803898");
        person1.setZip(111);
        person1.setMedicalHistory(new MedicalRecord());
        listPerson.put("person1", person1);
        return listPerson;
    }

    //return list houses classed by address
    @GetMapping("/flood/stations")
    public String listStations(@RequestParam(name="listStations", required = false, defaultValue = "None")List listStations, Model model) {
        model.addAttribute("listStations", listStations);
        return "stations";
    }

    //return person informations
    @GetMapping("/personInfo")
    public String personInfo(@RequestParam(name="person", required = false, defaultValue = "None")String firstName,@RequestParam(name="person", required = false, defaultValue = "None")String lastName, Model model) {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        model.addAttribute("listStations", person);
        return "personInfo";
    }

    //return list of all email of a city
    @GetMapping("/communityEmail")
    public String listStations(@RequestParam(name="city", required = false, defaultValue = "None")String city, Model model) {
        model.addAttribute("city", city);
        return "communityEmail";
    }

}
