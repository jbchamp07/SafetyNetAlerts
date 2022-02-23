package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.service.PersonServices;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    final PersonServices personServices;

    public PersonController(PersonServices personServices) {
        this.personServices = personServices;
    }

    @PostMapping("/person")
    public String personPost(@RequestBody String firstName, @RequestParam String lastName, @RequestParam String address, @RequestParam String city, @RequestParam int zip, @RequestParam String phone, @RequestParam String email) {
        personServices.addAPerson(firstName,lastName,address,city,zip,phone,email);
        return "person added";
    }
    @DeleteMapping("/person/{firstName}/{lastName}")
    public String personDelete(@PathVariable String firstName, @PathVariable String lastName) {
        personServices.findAPerson(firstName,lastName);
        return firstName + " " + lastName + " deleted";
    }
    @PutMapping("/person/{firstName}/{lastName}/{address}/{city}/{zip}/{phone}/{email}")
    public String personPut(@PathVariable String firstName, @PathVariable String lastName, @PathVariable String address, @PathVariable String city, @PathVariable int zip, @PathVariable String phone, @PathVariable String email) {
        personServices.updateAPerson(firstName,lastName,address,city,zip,phone,email);
        return firstName + " " + lastName + " updated";
    }

}
