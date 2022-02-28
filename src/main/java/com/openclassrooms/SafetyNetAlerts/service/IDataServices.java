package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.SafetyNetAlerts.model.Person;

import java.util.Arrays;
import java.util.List;

public interface IDataServices {


    List<Person> getListPersons2();
    List<Person> listPersonOfAFireStation(int stationNumber);
    List<String> listPhoneOfAFireStation(int stationNumber);
    List<String> listEmailOfACity(String city);

    void addPerson(Person person);
    void deletePerson(String firstName, String lastName);
    void updatePerson(Person person);

    void addFireStation(FireStation fireStation);
    void deleteFireStation(FireStation fireStation);
    void updateFireStation(FireStation fireStation);

    void addMedicalRecord(MedicalRecord medicalRecord);
    void deleteMedicalRecord(String firstName, String lastName);
    void updateMedicalRecord(MedicalRecord medicalRecord);

    List<Person> kidsOfAHouse(String address);
    List<Person> aPerson(String firstName, String lastName);

    List<Person> personsFromFireStations(List listStations);
}
