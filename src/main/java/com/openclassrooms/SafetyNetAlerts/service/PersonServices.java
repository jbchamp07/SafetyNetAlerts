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

    public List<String> findPhoneByFireStation(int fireStation){
        return dataServices.listPhoneOfAFireStation(fireStation);
    }

    public List<String> findEmailByCity(String city){
        return dataServices.listEmailOfACity(city);
    }

    public void findAPerson(String firstName,String lastName){
        dataServices.deletePerson(firstName,lastName);
    }

    public void addAPerson(String firstName,String lastName,String address,String city, int zip, String phone, String email){
        dataServices.addPerson(firstName,lastName,address,city,zip,phone,email);
    }

    public void updateAPerson(String firstName,String lastName,String address,String city, int zip, String phone, String email){
        dataServices.updatePerson(firstName,lastName,address,city,zip,phone,email);
    }

}
