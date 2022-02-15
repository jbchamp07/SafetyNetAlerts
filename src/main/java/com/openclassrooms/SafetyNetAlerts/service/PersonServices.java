package com.openclassrooms.SafetyNetAlerts.service;

import com.google.gson.Gson;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class PersonServices {

    private final DataServices dataServices;

    public PersonServices(DataServices dataServices) {
        this.dataServices = dataServices;
    }

    public List<Person> findPersonByAddress(String address){
        List<JSONObject> listPersons = dataServices.getListPersons();
        Gson gson = new Gson();
        List<Person> personList2 = listPersons.stream().map(item -> gson.fromJson(item.toString(), Person.class)).collect(Collectors.toList());
        return personList2.stream().filter(item -> item.getAddress().toUpperCase().equals(address.toUpperCase())).collect(Collectors.toList());
    }

}
