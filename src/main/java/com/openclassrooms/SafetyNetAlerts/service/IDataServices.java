package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.dto.AddressDTO;
import com.openclassrooms.SafetyNetAlerts.dto.PersonDTOPersonInfo;
import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.SafetyNetAlerts.model.Person;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public interface IDataServices {


    List<Person> getListPersons2();
    List<FireStation> getListFireStations2();
    List<MedicalRecord> getListMedicalRecords2();
    HashMap listPersonOfAFireStation(int stationNumber);
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

    HashMap<String,List<Person>> kidsOfAHouse(String address);
    List<PersonDTOPersonInfo> aPerson(String firstName, String lastName);
    List<AddressDTO> personsFromFireStations(List listStations);
    HashMap<String,List<Person>> findPersonByAddress(String address);

}
