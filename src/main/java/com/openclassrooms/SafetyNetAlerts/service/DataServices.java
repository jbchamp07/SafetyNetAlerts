package com.openclassrooms.SafetyNetAlerts.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Service
@Slf4j
public class DataServices {

    private List<JSONObject> listPersons;
    private List<JSONObject> listMedicalRecords;
    private List<JSONObject> listFireStations;
    private List<Person> listPersons2;
    private List<MedicalRecord> listMedicalRecords2;
    private List<FireStation> listFireStations2;
    final String path = "C:\\Users\\jbcha\\Desktop\\Formation\\projet 5\\SafetyNetAlerts\\src\\main\\resources\\data.json";
    private List<Person> listPersonOfAFireStation;

    public DataServices() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(path));
        JSONObject jo = (JSONObject) obj;
        Gson gson = new Gson();

        listPersons = (List<JSONObject>) jo.get("persons");
        listMedicalRecords = (List<JSONObject>) jo.get("medicalrecords");
        listFireStations = (List<JSONObject>) jo.get("firestations");

        listPersons2 = listPersons.stream().map(item -> gson.fromJson(item.toString(), Person.class)).collect(Collectors.toList());
        listMedicalRecords2 = listMedicalRecords.stream().map(item -> gson.fromJson(item.toString(), MedicalRecord.class)).collect(Collectors.toList());
        listFireStations2 = listFireStations.stream().map(item -> gson.fromJson(item.toString(), FireStation.class)).collect(Collectors.toList());

        for (int i = 0; i < listPersons2.size(); i++) {
            for (int j = 0; j < listMedicalRecords2.size(); j++) {
                if (listPersons2.get(i).getFirstName().equals(listMedicalRecords2.get(j).getFirstName())) {
                    if (listPersons2.get(i).getLastName().equals(listMedicalRecords2.get(j).getLastName())) {
                        listPersons2.get(i).setMedicalHistory(listMedicalRecords2.get(j));
                    }
                }
            }
        }

    }

    private String fireStationAddress;
    public List<Person> listPersonOfAFireStation(int stationNumber) {
        for (int i = 0;i < listFireStations2.size();i++) {
            if(listFireStations2.get(i).getStation() == stationNumber){
                fireStationAddress = listFireStations2.get(i).getAddress();
            }
        }
        listPersonOfAFireStation = listPersons2.stream().filter(item -> item.getAddress().toUpperCase().equals(fireStationAddress.toUpperCase())).collect(Collectors.toList());
        return listPersonOfAFireStation;
    }

    public List<String> listPhoneOfAFireStation(int stationNumber) {
        listPersonOfAFireStation = listPersonOfAFireStation(stationNumber);
        List<String> listPhone = new ArrayList<>();
        for(int i = 0;i < listPersonOfAFireStation.size();i++){
            listPhone.add(listPersonOfAFireStation.get(i).getPhoneNumber());
        }
        return listPhone;
    }

    public List<String> listEmailOfACity(String city) {
        List<String> listEmail = new ArrayList<>();
        for(int i = 0;i < listPersons2.size();i++){
            if(listPersons2.get(i).getCity().toUpperCase().equals(city.toUpperCase())){
                listEmail.add(listPersons2.get(i).getEmail());
            }
        }
        return listEmail;
    }

    public void addPerson() {

    }

    public void updatePerson() {

    }

    public void deletePerson() {

    }

}
