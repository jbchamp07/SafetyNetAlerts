package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {

    //CRUD person
    @GetMapping("/person")
    public String person() {
        return "person";
    }

    //CRUD fireStation
    //return list person who has stationNumber1
    @GetMapping("/firestation")
    @ResponseBody
    public String firestation(@RequestParam(name="stationNumber", required=false, defaultValue="None") int stationNumber, Model model) {
        model.addAttribute("stationNumber", stationNumber);
        return "firestation";
    }

    //CRUD medicalRecord
    //
    @GetMapping("/medicalRecord")
    @ResponseBody
    public String medicalRecord() {
        return "medicalRecord";
    }

    //return list kids(18 or less) of an address
    @GetMapping("/childAlert")
    @ResponseBody
    public String childAlert(@RequestParam(name="address", required = false, defaultValue = "None")String address, Model model) {
        model.addAttribute("address", address);
        return "childAlert";
    }

    //
    @GetMapping("/phoneAlert")
    @ResponseBody
    public String phoneAlert(@RequestParam(name="firestationNumber", required = false, defaultValue = "None")int firestationNumber, Model model) {
        model.addAttribute("firestationNumber", firestationNumber);
        return "phoneAlert";
    }

    //
    @GetMapping("/fire")
    @ResponseBody
    public String fire(@RequestParam(name="address", required = false, defaultValue = "None")String address, Model model) {
        model.addAttribute("address", address);
        return "fire";
    }

    //
    @GetMapping("/flood/stations")
    @ResponseBody
    public String listStations(@RequestParam(name="listStations", required = false, defaultValue = "None")List listStations, Model model) {
        model.addAttribute("listStations", listStations);
        return "stations";
    }

    //
    @GetMapping("/personInfo")
    @ResponseBody
    public String personInfo(@RequestParam(name="person", required = false, defaultValue = "None")String firstName,@RequestParam(name="person", required = false, defaultValue = "None")String lastName, Model model) {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        model.addAttribute("listStations", person);
        return "personInfo";
    }

    //
    @GetMapping("/communityEmail")
    @ResponseBody
    public String listStations(@RequestParam(name="city", required = false, defaultValue = "None")String city, Model model) {
        model.addAttribute("city", city);
        return "communityEmail";
    }

}
