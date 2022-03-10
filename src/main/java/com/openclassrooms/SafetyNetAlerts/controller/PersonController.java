package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.service.PersonServices;
import org.springframework.web.bind.annotation.*;

/**
 * The type Person controller.
 */
@RestController
public class PersonController {

    /**
     * The Person services.
     */
    final PersonServices personServices;

    /**
     * Instantiates a new Person controller.
     *
     * @param personServices the person services
     */
    public PersonController(PersonServices personServices) {
        this.personServices = personServices;
    }

    /**
     * Person post string.
     *
     * @param person the person
     * @return the string
     */
    @PostMapping("/person")
    public String personPost(Person person) {
        personServices.addAPerson(person);
        return "person added";
    }

    /**
     * Person delete string.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the string
     */
    @DeleteMapping("/person")
    public String personDelete(@RequestParam String firstName, @RequestParam String lastName) {
        personServices.findAPerson(firstName,lastName);
        return firstName + " " + lastName + " deleted";
    }

    /**
     * Person put string.
     *
     * @param person the person
     * @return the string
     */
    @PutMapping("/person")
    public String personPut(Person person) {
        personServices.updateAPerson(person);
        return person.getFirstName() + " " + person.getLastName() + " updated";
    }

}
