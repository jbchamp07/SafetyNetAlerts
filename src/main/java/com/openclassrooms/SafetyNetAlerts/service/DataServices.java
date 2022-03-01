package com.openclassrooms.SafetyNetAlerts.service;

import com.google.gson.Gson;
import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Service
@Slf4j
public class DataServices implements IDataServices{

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

        listPersons2 = ((List<JSONObject>) jo.get("persons")).stream().map(item -> gson.fromJson(item.toString(), Person.class)).collect(Collectors.toList());;
        listMedicalRecords2 = ((List<JSONObject>) jo.get("medicalrecords")).stream().map(item -> gson.fromJson(item.toString(), MedicalRecord.class)).collect(Collectors.toList());
        listFireStations2 = ((List<JSONObject>) jo.get("firestations")).stream().map(item -> gson.fromJson(item.toString(), FireStation.class)).collect(Collectors.toList());


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
    //A REVOIR
    @Override
    public List<Person> listPersonOfAFireStation(int stationNumber) {
        for (int i = 0;i < listFireStations2.size();i++) {
            if(listFireStations2.get(i).getStation() == stationNumber){
                fireStationAddress = listFireStations2.get(i).getAddress();
            }
        }
        listPersonOfAFireStation = listPersons2.stream().filter(item -> item.getAddress().toUpperCase().equals(fireStationAddress.toUpperCase())).collect(Collectors.toList());
        return listPersonOfAFireStation;
    }
    @Override
    public List<String> listPhoneOfAFireStation(int stationNumber) {
        listPersonOfAFireStation = listPersonOfAFireStation(stationNumber);
        List<String> listPhone = new ArrayList<>();
        for(int i = 0;i < listPersonOfAFireStation.size();i++){
            if(listPhone.contains(listPersonOfAFireStation.get(i).getPhoneNumber())){

            }else{
                listPhone.add(listPersonOfAFireStation.get(i).getPhoneNumber());
            }
        }
        return listPhone;
    }
    @Override
    public List<String> listEmailOfACity(String city) {
        List<String> listEmail = new ArrayList<>();
        for(int i = 0;i < listPersons2.size();i++){
            if(listPersons2.get(i).getCity().toUpperCase().equals(city.toUpperCase())){
                if(listEmail.contains(listPersons2.get(i).getEmail())){

                }else{
                    listEmail.add(listPersons2.get(i).getEmail());
                }
            }
        }
        return listEmail;
    }
    @Override
    public void updatePerson(Person person) {
        Person oldPerson = new Person();
        for(int i = 0;i < listPersons2.size();i++){
            if(listPersons2.get(i).getFirstName().toUpperCase().equals(person.getFirstName().toUpperCase())){
                if(listPersons2.get(i).getLastName().toUpperCase().equals(person.getLastName().toUpperCase())){
                    oldPerson = listPersons2.get(i);
                }
            }
        }
        listPersons2.remove(oldPerson);
        listPersons2.add(person);
    }
    @Override
    public void addPerson(Person person){
        listPersons2.add(person);
    }
    @Override
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

    }

    public void updateJsonFile(){
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
            //file.write("{\n" + "    \"persons\": " + listPersonJson.toJSONString() + ",\n\"firestations\": " + listFirestationJson + ",\n\"medicalrecords\": " + listMedicalRecordJson + "}");
            HashMap<String,String> m = new HashMap();
            m.put("persons",listPersonJson.toString());
            m.put("firestations",listFirestationJson.toString());
            m.put("medicalrecords",listMedicalRecordJson.toString());
            file.write(String.valueOf(m));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }/*
        /*try (FileWriter file = new FileWriter("C:\\Users\\jbcha\\Desktop\\Formation\\projet 5\\SafetyNetAlerts\\src\\main\\resources\\data2.json")) {
            file.write(String.valueOf(jo));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }


    @Override
    public void addFireStation(FireStation fireStation) {
        listFireStations2.add(fireStation);
    }
    @Override
    public void deleteFireStation(FireStation fireStation) {
        /*fireStation fireStation = null;
        for(int i = 0;i < listFireStations2.size();i++) {
            if( (listFireStations2.get(i).getAddress().equals(fireStation.getAddress())) && (listFireStations2.get(i).getStation() == fireStation.getStation()) ) {
                fireStation = listFireStations2.get(i);
            }
        }*/
        listFireStations2.remove(fireStation);
    }
    @Override
    public void updateFireStation(FireStation fireStation) {
        FireStation oldFireStation = new FireStation();
        for(int i = 0;i < listFireStations2.size();i++){
            if(listFireStations2.get(i).getAddress().toUpperCase().equals(fireStation.getAddress().toUpperCase())){
                oldFireStation = listFireStations2.get(i);
            }
        }
        listFireStations2.remove(oldFireStation);
        listFireStations2.add(fireStation);
    }

    @Override
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        listMedicalRecords2.add(medicalRecord);
    }
    @Override
    public void deleteMedicalRecord(String firstName, String lastName) {
        MedicalRecord medicalRecord = null;
        for(int i = 0;i < listMedicalRecords2.size();i++) {
            if( (listMedicalRecords2.get(i).getFirstName().toUpperCase().equals(firstName.toUpperCase())) && (listMedicalRecords2.get(i).getLastName().toUpperCase().equals(lastName.toUpperCase())) ) {
                medicalRecord = listMedicalRecords2.get(i);
            }
        }
        listMedicalRecords2.remove(medicalRecord);
    }
    @Override
    public void updateMedicalRecord(MedicalRecord medicalRecord) {
        MedicalRecord oldMedicalRecord = new MedicalRecord();
        for(int i = 0;i < listFireStations2.size();i++){
            if( (listMedicalRecords2.get(i).getFirstName().toUpperCase().equals(medicalRecord.getFirstName().toUpperCase())) && (listMedicalRecords2.get(i).getLastName().toUpperCase().equals(medicalRecord.getLastName().toUpperCase()))){
                oldMedicalRecord = listMedicalRecords2.get(i);
            }
        }
        listMedicalRecords2.remove(oldMedicalRecord);
        listMedicalRecords2.add(medicalRecord);
    }
    @Override
    public List<Person> getListPersons2(){
        return listPersons2;
    }
    //A REVOIR
    @Override
    public List<Person> kidsOfAHouse(String address){
        List<Person> listAdultOfAHouse = new ArrayList<>();
        List<Person> listKidsOfAHouse = new ArrayList<>();
        for(int i = 0;i < listPersons2.size();i++){
            if(listPersons2.get(i).getAddress().equals(address)){
                listAdultOfAHouse.add(listPersons2.get(i));
            }
        }
        /*
        HashMap<String,List<Person>> map = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate localDate = null;
        Period period = null;
        for(int i = 0;i < listAdultOfAHouse.size();i++){
            localDate.parse(listAdultOfAHouse.get(i).getMedicalHistory().getBirthdate(), formatter);
            period = Period.between(localDate, LocalDate.now());
            if(period.getYears() <= 18){
                listKidsOfAHouse.add(listAdultOfAHouse.get(i));
                listAdultOfAHouse.remove(listAdultOfAHouse.get(i));
            }
        }

        map.put("kids",listKidsOfAHouse);
        map.put("adults",listAdultOfAHouse);
        String mapAsString = map.keySet().stream()
                .map(key -> key + "=" + map.get(key).stream().collect(Collectors.toList()))
                .collect(Collectors.joining(", ", "{", "}"));
        */
        String s;
        int index;
        for(int i = 0;i < listAdultOfAHouse.size();i++){
            index = listAdultOfAHouse.get(i).getMedicalHistory().getBirthdate().lastIndexOf("/");
            s = listAdultOfAHouse.get(i).getMedicalHistory().getBirthdate().substring(index + 1);
            if( (2022 -  Integer.valueOf(s)) <= 18){
                listKidsOfAHouse.add(listAdultOfAHouse.get(i));
            }
        }

        listAdultOfAHouse.removeAll(listKidsOfAHouse);

        List<List<Person>> les2Listes = new ArrayList<>();
        les2Listes.add(listKidsOfAHouse);
        les2Listes.add(listAdultOfAHouse);

        HashMap<String,List<Person>> m = new HashMap();
        m.put("kids",listKidsOfAHouse);
        m.put("adults",listAdultOfAHouse);

        return m;
    }
    @Override
    public List<Person> aPerson(String firstName, String lastName){
        return listPersons2.stream().filter(item -> item.getFirstName().toUpperCase().equals(firstName.toUpperCase())).filter(item -> item.getLastName().toUpperCase().equals(lastName.toUpperCase())).collect(Collectors.toList());
    }

    @Override
    public List<Person> personsFromFireStations(List listStations){
        List<FireStation> listStationDeserved = new ArrayList<>();
        List<Person> listPersonDeserved = new ArrayList<>();

        for (int i = 0;i < listStations.size();i++) {
            for (int j = 0;j < listFireStations2.size();j++) {
                if(listFireStations2.get(j).getStation() == Integer.parseInt(listStations.get(i).toString())){
                    listStationDeserved.add(listFireStations2.get(j));
                }
            }
        }
        for (int i = 0;i < listPersons2.size();i++){
            for (int j = 0;j < listStationDeserved.size();j++) {
                if(listPersons2.get(i).getAddress().toUpperCase().equals(listStationDeserved.get(j).getAddress().toUpperCase())){
                    listPersonDeserved.add(listPersons2.get(i));
                }
            }
        }

        return listPersonDeserved;
    }

}
