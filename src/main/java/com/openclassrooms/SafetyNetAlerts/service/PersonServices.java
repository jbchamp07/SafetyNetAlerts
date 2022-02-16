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
        //List<JSONObject> listPersons = dataServices.getListPersons();
        return dataServices.getListPersons2().stream().filter(item -> item.getAddress().toUpperCase().equals(address.toUpperCase())).collect(Collectors.toList());
    }

    public List<Person> allPersons(){
        return dataServices.getListPersons2().stream().collect(Collectors.toList());
    }

    public List<Person> findPersonByFireStation(int fireStation){
        return dataServices.listPersonOfAFireStation(fireStation);
    }

}
