package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.service.PersonServices;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    final PersonServices personServices;

    public PersonController(PersonServices personServices) {
        this.personServices = personServices;
    }

    @PostMapping("/person")
    public String personPost(Person person) {
        personServices.addAPerson(person);
        return "person added";
    }
    @DeleteMapping("/person")
    public String personDelete(@RequestParam String firstName, @RequestParam String lastName) {
        personServices.findAPerson(firstName,lastName);
        return firstName + " " + lastName + " deleted";
    }
    @PutMapping("/person")
    public String personPut(Person person) {
        personServices.updateAPerson(person);
        return person.getFirstName() + " " + person.getLastName() + " updated";
    }

}
