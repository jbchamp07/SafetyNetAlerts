package com.openclassrooms.SafetyNetAlerts.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
import java.io.FileWriter;
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

    private JSONParser parser;
    private Object obj;
    private JSONObject jo;
    private Gson gson;

    public DataServices() throws IOException, ParseException {
        parser = new JSONParser();
        obj = parser.parse(new FileReader(path));
        jo = (JSONObject) obj;
        gson = new Gson();

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

    public void updatePerson(String firstName, String lastName,String address,String city, int zip, String phone, String email) {
        Person person = new Person();
        for(int i = 0;i < listPersons2.size();i++){
            if(listPersons2.get(i).getFirstName().toUpperCase().equals(firstName.toUpperCase())){
                if(listPersons2.get(i).getLastName().toUpperCase().equals(lastName.toUpperCase())){
                    person = listPersons2.get(i);
                }
            }
        }
        listPersons2.remove(person);
        person.setAddress(address);
        person.setCity(city);
        person.setZip(zip);
        person.setPhoneNumber(phone);
        person.setEmail(email);
        listPersons2.add(person);
        updateJsonFile();

    }

    public void addPerson(String firstName, String lastName,String address,String city, int zip, String phone, String email){
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setAddress(address);
        person.setCity(city);
        person.setZip(zip);
        person.setPhoneNumber(phone);
        person.setEmail(email);
        listPersons2.add(person);
        jo.put("person",person);
        updateJsonFile();
    }

    public void deletePerson(String firstName, String lastName) {
        Person person = null;
        //person = listPersons2.stream().filter(item -> item.getFirstName().toUpperCase().equals(firstName.toUpperCase())).findFirst().get();

        for(int i = 0;i < listPersons2.size();i++) {
            if( (listPersons2.get(i).getFirstName().equals(firstName) ) && ( listPersons2.get(i).getLastName().equals(lastName)) ) {
                person = listPersons2.get(i);
            }
        }
        listPersons2.remove(person);
        listMedicalRecords2.remove(person.getMedicalHistory());
        jo.remove(person);

        updateJsonFile();

        /*JSONArray listPersonJson = new JSONArray();
        JSONArray listFirestationJson = new JSONArray();
        JSONArray listMedicalRecordJson = new JSONArray();
        JSONObject jsonObject;
        for(int i = 0; i < listPersons2.size();i++) {
            jsonObject = new JSONObject();
            jsonObject.put("firstName", listPersons2.get(i).getFirstName());
            jsonObject.put("lastName", listPersons2.get(i).getLastName());
            jsonObject.put("address", listPersons2.get(i).getAddress());
            jsonObject.put("city", listPersons2.get(i).getCity());
            jsonObject.put("zip", listPersons2.get(i).getZip());
            jsonObject.put("phone", listPersons2.get(i).getPhoneNumber());
            jsonObject.put("email", listPersons2.get(i).getEmail());
            listPersonJson.add(jsonObject);
        }
        for(int i = 0; i < listFireStations2.size();i++) {
            jsonObject = new JSONObject();
            jsonObject.put("address", listFireStations2.get(i).getAddress());
            jsonObject.put("station", listFireStations2.get(i).getStation());
            listFirestationJson.add(jsonObject);
        }
        for(int i = 0; i < listMedicalRecords2.size();i++) {
            jsonObject = new JSONObject();
            jsonObject.put("firstName", listMedicalRecords2.get(i).getFirstName());
            jsonObject.put("lastName", listMedicalRecords2.get(i).getLastName());
            jsonObject.put("birthdate", listMedicalRecords2.get(i).getBirthDate());
            jsonObject.put("medications", listMedicalRecords2.get(i).getMedications());
            jsonObject.put("allergies", listMedicalRecords2.get(i).getAllergies());
            listMedicalRecordJson.add(jsonObject);
        }

        try (FileWriter file = new FileWriter("C:\\Users\\jbcha\\Desktop\\Formation\\projet 5\\SafetyNetAlerts\\src\\main\\resources\\data2.json")) {
            file.write("{\n" + "    \"persons\": " + listPersonJson.toJSONString() + "\n\",firestations\": " + listFirestationJson + "\n\",medicalrecords\": " + listMedicalRecordJson + "}");
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    public void updateJsonFile(){
        try (FileWriter file = new FileWriter("C:\\Users\\jbcha\\Desktop\\Formation\\projet 5\\SafetyNetAlerts\\src\\main\\resources\\data2.json")) {
            file.write(String.valueOf(jo));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
