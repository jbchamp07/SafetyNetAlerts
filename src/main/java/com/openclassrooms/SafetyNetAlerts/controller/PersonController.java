package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.controller.requests.PersonRequest;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.service.PersonServices;
import org.modelmapper.ModelMapper;
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
     * @return the string
     */
    @PostMapping("/person")
    public String personPost(@RequestBody PersonRequest personRequest) {
        ModelMapper modelMapper = new ModelMapper();
        Person person = modelMapper.map(personRequest,Person.class);
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
     * @return the string
     */
    @PutMapping("/person")
    public String personPut(@RequestBody PersonRequest personRequest) {
        ModelMapper modelMapper = new ModelMapper();
        Person person = modelMapper.map(personRequest,Person.class);
        personServices.updateAPerson(person);
        return person.getFirstName() + " " + person.getLastName() + " updated";
    }

}
