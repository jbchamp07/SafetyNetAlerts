package com.openclassrooms.SafetyNetAlerts.service;

import com.google.gson.Gson;
import com.openclassrooms.SafetyNetAlerts.dto.*;
import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
    final String path = "src\\main\\resources\\data.json";
    private List<Person> listPersonOfAFireStation;

    private JSONParser parser;
    private Object obj;
    private JSONObject jo;
    private Gson gson;
    private ModelMapper modelMapper;

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

    @Override
    public HashMap listPersonOfAFireStation(int stationNumber) {
        listPersonOfAFireStation = new ArrayList<>();
        List<String> listAddress = new ArrayList<>();
        for (int i = 0;i < listFireStations2.size();i++) {
            if(listFireStations2.get(i).getStation() == stationNumber){
                if(listAddress.contains(listFireStations2.get(i).getAddress())){
                }else{
                    listAddress.add(listFireStations2.get(i).getAddress());
                }
            }
        }
        //listPersonOfAFireStation = listPersons2.stream().filter(item -> item.getAddress().toUpperCase().equals(listAddress.stream())).collect(Collectors.toList());
        for (int i = 0;i < listPersons2.size();i++) {
            for(int j = 0; j < listAddress.size();j++){
                if(listPersons2.get(i).getAddress().equals(listAddress.get(j))){
                    listPersonOfAFireStation.add(listPersons2.get(i));
                }
            }
        }

        String s;
        int index;
        int nbKids = 0;
        int nbAdults = 0;
        for(int i = 0;i < listPersonOfAFireStation.size();i++){
            index = listPersonOfAFireStation.get(i).getMedicalHistory().getBirthdate().lastIndexOf("/");
            s = listPersonOfAFireStation.get(i).getMedicalHistory().getBirthdate().substring(index + 1);
            if( (2022 -  Integer.valueOf(s)) <= 18){
                nbKids = nbKids + 1;
            }
        }
        nbAdults = listPersonOfAFireStation.size() - nbKids;
        HashMap m = new HashMap();
        m.put("Numbers of kids",nbKids);
        m.put("Numbers of adults",nbAdults);
        //m.put("persons",listPersonOfAFireStation);
        modelMapper = new ModelMapper();
        List<PersonDTOFireStation> personDTOFireStation = new ArrayList<>();
        for(int i = 0; i < listPersonOfAFireStation.size();i++){
            personDTOFireStation.add(modelMapper.map(listPersonOfAFireStation.get(i), PersonDTOFireStation.class));
        }
        m.put("persons",personDTOFireStation);
        return  m;
        //return listPersonOfAFireStation;

    }
    @Override
    public List<String> listPhoneOfAFireStation(int stationNumber) {
        HashMap m = new HashMap();
        m = listPersonOfAFireStation(stationNumber);
        listPersonOfAFireStation = (List<Person>) m.get("persons");

        modelMapper = new ModelMapper();
        List<PersonDTOFireStation> personDTOFireStation = new ArrayList<>();
        for(int i = 0; i < listPersonOfAFireStation.size();i++){
            personDTOFireStation.add(modelMapper.map(listPersonOfAFireStation.get(i), PersonDTOFireStation.class));
        }

        List<String> listPhone = new ArrayList<>();
        for(int i = 0;i < personDTOFireStation.size();i++){
            if(listPhone.contains(personDTOFireStation.get(i).getPhoneNumber())){

            }else{
                listPhone.add(personDTOFireStation.get(i).getPhoneNumber());
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

    /*public void updateJsonFile(){
        JSONArray listPersonJson = new JSONArray();
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
        }
    }*/


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
        for (int i = 0; i < listPersons2.size(); i++) {
            if( (listPersons2.get(i).getFirstName().equals(medicalRecord.getFirstName())) && (listPersons2.get(i).getLastName().equals(medicalRecord.getLastName()))){
                listPersons2.get(i).setMedicalHistory(medicalRecord);
            }
        }
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
        for (int i = 0; i < listPersons2.size(); i++) {
            if( (listPersons2.get(i).getFirstName().equals(medicalRecord.getFirstName())) && (listPersons2.get(i).getLastName().equals(medicalRecord.getLastName()))){
                listPersons2.get(i).setMedicalHistory(null);
            }
        }
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
        for (int i = 0; i < listPersons2.size(); i++) {
            if( (listPersons2.get(i).getFirstName().equals(medicalRecord.getFirstName())) && (listPersons2.get(i).getLastName().equals(medicalRecord.getLastName()))){
                listPersons2.get(i).setMedicalHistory(medicalRecord);
            }
        }
    }
    @Override
    public List<Person> getListPersons2(){
        return listPersons2;
    }
    @Override
    public List<FireStation> getListFireStations2(){
        return listFireStations2;
    }
    @Override
    public List<MedicalRecord> getListMedicalRecords2(){
        return listMedicalRecords2;
    }
    //A REVOIR calcul de l'age
    @Override
    public HashMap<String,List<Person>> kidsOfAHouse(String address){
        List<Person> listAdultOfAHouse = new ArrayList<>();
        List<Person> listKidsOfAHouse = new ArrayList<>();
        for(int i = 0;i < listPersons2.size();i++){
            if(listPersons2.get(i).getAddress().equals(address)){
                listAdultOfAHouse.add(listPersons2.get(i));
            }
        }
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

        HashMap m = new HashMap();
        //m.put("kids",listKidsOfAHouse);
        //m.put("adults",listAdultOfAHouse);

        modelMapper = new ModelMapper();
        List<PersonDTOChildAlert> personDTOChildAlertsKids = new ArrayList<>();
        List<PersonDTOChildAlert> personDTOChildAlertsAdults = new ArrayList<>();
        int age;
        for(int i = 0; i < listKidsOfAHouse.size();i++){
            personDTOChildAlertsKids.add(modelMapper.map(listKidsOfAHouse.get(i), PersonDTOChildAlert.class));
            age = (Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(listKidsOfAHouse.get(i).getMedicalHistory().getBirthdate().substring(6)));
            personDTOChildAlertsKids.get(i).setAge(age);
        }
        for(int i = 0; i < listAdultOfAHouse.size();i++){
            personDTOChildAlertsAdults.add(modelMapper.map(listAdultOfAHouse.get(i), PersonDTOChildAlert.class));
            age = (Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(listAdultOfAHouse.get(i).getMedicalHistory().getBirthdate().substring(6)));
            personDTOChildAlertsAdults.get(i).setAge(age);
        }
        m.put("kids",personDTOChildAlertsKids);
        m.put("adults",personDTOChildAlertsAdults);

        return m;
    }
    @Override
    public List<PersonDTOPersonInfo> aPerson(String firstName, String lastName){
        List<Person> listPerson = listPersons2.stream().filter(item -> item.getFirstName().toUpperCase().equals(firstName.toUpperCase())).filter(item -> item.getLastName().toUpperCase().equals(lastName.toUpperCase())).collect(Collectors.toList());
        modelMapper = new ModelMapper();
        List<PersonDTOPersonInfo> personDTOPersonInfo = new ArrayList<>();
        int age;
        for(int i = 0; i < listPerson.size();i++){
            personDTOPersonInfo.add(modelMapper.map(listPerson.get(i), PersonDTOPersonInfo.class));
            age = (Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(listPerson.get(i).getMedicalHistory().getBirthdate().substring(6)));
            personDTOPersonInfo.get(i).setAge(age);
        }
        return personDTOPersonInfo;
    }

    @Override
    public List<AddressDTO> personsFromFireStations(List listStations){
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

        modelMapper = new ModelMapper();
        List<PersonDTOStations> personDTOStations = new ArrayList<>();
        for(int i = 0; i < listPersonDeserved.size();i++){
            personDTOStations.add(modelMapper.map(listPersonDeserved.get(i), PersonDTOStations.class));
        }

        AddressDTO addressDTO;
        List<AddressDTO> list = new ArrayList<>();
        for(int i = 0; i < listStationDeserved.size();i++){
            addressDTO = new AddressDTO();
            addressDTO.setAddress(listStationDeserved.get(i).getAddress());
            int j = i;
            addressDTO.setPersons(personDTOStations.stream().filter(t -> t.getAddress().equals(listStationDeserved.get(j).getAddress())).collect(Collectors.toList()));
            list.add(addressDTO);
        }

        return list;
    }

    @Override
    public HashMap<String,List<Person>> findPersonByAddress(String address){
        List<Person> listPersonOfAnAddress = new ArrayList<>();
        FireStation fireStation = new FireStation();
        for(int i = 0; i < listFireStations2.size();i++){
            if(listFireStations2.get(i).getAddress().toUpperCase().equals(address.toUpperCase())){
                fireStation =listFireStations2.get(i);
            }
        }
        listPersonOfAnAddress = getListPersons2().stream().filter(item -> item.getAddress().toUpperCase().equals(address.toUpperCase())).collect(Collectors.toList());
        HashMap m = new HashMap();
        m.put("stationNumber",fireStation.getStation());
        //m.put("persons",listPersonOfAnAddress);
        modelMapper = new ModelMapper();
        List<PersonDTOFire> personDTOFire = new ArrayList<>();
        int age;
        for(int i = 0; i < listPersonOfAnAddress.size();i++){
            personDTOFire.add(modelMapper.map(listPersonOfAnAddress.get(i), PersonDTOFire.class));
            age = (Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(listPersonOfAnAddress.get(i).getMedicalHistory().getBirthdate().substring(6)));
            personDTOFire.get(i).setAge(age);
        }
        m.put("persons",personDTOFire);
        return m;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataServices that = (DataServices) o;
        return Objects.equals(listPersons, that.listPersons) && Objects.equals(listMedicalRecords, that.listMedicalRecords) && Objects.equals(listFireStations, that.listFireStations) && Objects.equals(listPersons2, that.listPersons2) && Objects.equals(listMedicalRecords2, that.listMedicalRecords2) && Objects.equals(listFireStations2, that.listFireStations2) && Objects.equals(path, that.path) && Objects.equals(listPersonOfAFireStation, that.listPersonOfAFireStation) && Objects.equals(parser, that.parser) && Objects.equals(obj, that.obj) && Objects.equals(jo, that.jo) && Objects.equals(gson, that.gson) && Objects.equals(modelMapper, that.modelMapper);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listPersons, listMedicalRecords, listFireStations, listPersons2, listMedicalRecords2, listFireStations2, path, listPersonOfAFireStation, parser, obj, jo, gson, modelMapper);
    }

    @Override
    public String toString() {
        return "DataServices{" +
                "listPersons=" + listPersons +
                ", listMedicalRecords=" + listMedicalRecords +
                ", listFireStations=" + listFireStations +
                ", listPersons2=" + listPersons2 +
                ", listMedicalRecords2=" + listMedicalRecords2 +
                ", listFireStations2=" + listFireStations2 +
                ", path='" + path + '\'' +
                ", listPersonOfAFireStation=" + listPersonOfAFireStation +
                ", parser=" + parser +
                ", obj=" + obj +
                ", jo=" + jo +
                ", gson=" + gson +
                ", modelMapper=" + modelMapper +
                '}';
    }
}
