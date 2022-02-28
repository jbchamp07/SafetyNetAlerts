package com.openclassrooms.SafetyNetAlerts.serviceTest;

import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import com.openclassrooms.SafetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.service.DataServices;
import com.openclassrooms.SafetyNetAlerts.service.IDataServices;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
public class DataServicesTest {

    private DataServices dataServices;

    @Before
    public void init() throws IOException, ParseException {
        dataServices = new DataServices();
    }

    @BeforeEach
    public void initDataServices() throws IOException, ParseException {
        dataServices = new DataServices();
    }

    //A FAIRE
    @Test
    public void listPersonOfAFireStationTest(){
        List<Person> listPersonOfAFireStation = new ArrayList<>();
        //listPersonOfAFireStation.add();
        assertEquals(listPersonOfAFireStation,dataServices.listPersonOfAFireStation(1));
    }

    @Test
    public void listPhoneOfAFireStationTest(){
        List<String> listPhone = new ArrayList<>();
        listPhone.add("841-874-7784");
        assertEquals(listPhone,dataServices.listPhoneOfAFireStation(1));
    }

    @Test
    public void listEmailOfACityTestAll(){
        List<String> listEmail = new ArrayList<>();
        listEmail.add("jaboyd@email.com");
        listEmail.add("drk@email.com");
        listEmail.add("tenz@email.com");
        listEmail.add("tcoop@ymail.com");
        listEmail.add("lily@email.com");
        listEmail.add("soph@email.com");
        listEmail.add("ward@email.com");
        listEmail.add("zarc@email.com");
        listEmail.add("reg@email.com");
        listEmail.add("jpeter@email.com");
        listEmail.add("aly@imail.com");
        listEmail.add("bstel@email.com");
        listEmail.add("ssanw@email.com");
        listEmail.add("clivfd@ymail.com");
        listEmail.add("gramps@email.com");
        assertEquals(listEmail,dataServices.listEmailOfACity("Culver"));
    }

    @Test
    public void listEmailOfACityTestNone(){
        List<String> listEmail = new ArrayList<>();
        assertEquals(listEmail,dataServices.listEmailOfACity("None"));
    }

    @Test
    public void updatePersonTest(){
        Person person = new Person();
        person.setFirstName("Jean-Baptiste");
        person.setLastName("Champetier");
        person.setEmail("jb.champetier@gmail.com");
        person.setPhoneNumber("0610803898");
        person.setCity("Paris");
        person.setZip(719);
        person.setAddress("123 rue lafayette");
        person.setMedicalHistory(new MedicalRecord());
        dataServices.addPerson(person);
        person.setAddress("456 rue lafayette");
        dataServices.updatePerson(person);
        assertEquals("456 rue lafayette",dataServices.getListPersons2().get(dataServices.getListPersons2().size() - 1).getAddress());
    }
    @Test
    public void addPersonTest(){
        Person person = new Person();
        person.setFirstName("Jean-Baptiste");
        person.setLastName("Champetier");
        person.setEmail("jb.champetier@gmail.com");
        person.setPhoneNumber("0610803898");
        person.setCity("Paris");
        person.setZip(719);
        person.setAddress("123 rue lafayette");
        person.setMedicalHistory(new MedicalRecord());
        dataServices.addPerson(person);
        assertEquals("Jean-Baptiste",dataServices.getListPersons2().get(dataServices.getListPersons2().size() - 1).getFirstName());
        assertEquals("Champetier",dataServices.getListPersons2().get(dataServices.getListPersons2().size() - 1).getLastName());
        assertEquals("jb.champetier@gmail.com",dataServices.getListPersons2().get(dataServices.getListPersons2().size() - 1).getEmail());
        assertEquals("0610803898",dataServices.getListPersons2().get(dataServices.getListPersons2().size() - 1).getPhoneNumber());
        assertEquals("Paris",dataServices.getListPersons2().get(dataServices.getListPersons2().size() - 1).getCity());
        assertEquals(719,dataServices.getListPersons2().get(dataServices.getListPersons2().size() - 1).getZip());
        assertEquals("123 rue lafayette",dataServices.getListPersons2().get(dataServices.getListPersons2().size() - 1).getAddress());
    }
    @Test
    public void deletePersonTestOk(){
        Person person = new Person();
        person.setFirstName("Jean-Baptiste");
        person.setLastName("Champetier");
        person.setEmail("jb.champetier@gmail.com");
        person.setPhoneNumber("0610803898");
        person.setCity("Paris");
        person.setZip(719);
        person.setAddress("123 rue lafayette");
        person.setMedicalHistory(new MedicalRecord());
        dataServices.addPerson(person);
        dataServices.deletePerson(person.getFirstName(),person.getLastName());
        assertEquals(false,dataServices.getListPersons2().contains(person));
    }
    @Test
    public void deletePersonTestNotOk(){
        Person person = new Person();
        person.setFirstName("Jean-Baptiste");
        person.setLastName("Champetier");
        person.setEmail("jb.champetier@gmail.com");
        person.setPhoneNumber("0610803898");
        person.setCity("Paris");
        person.setZip(719);
        person.setAddress("123 rue lafayette");
        person.setMedicalHistory(new MedicalRecord());
        dataServices.addPerson(person);
        assertEquals(true,dataServices.getListPersons2().contains(person));
    }

    @Test
    public void addFireStationTest(){
        FireStation fireStation = new FireStation();
        fireStation.setStation(10);
        fireStation.setAddress("789 boulevard gambetta");
        dataServices.addFireStation(fireStation);
        assertEquals(10,dataServices.getListFireStations2().get(dataServices.getListFireStations2().size() - 1).getStation());
        assertEquals("789 boulevard gambetta",dataServices.getListFireStations2().get(dataServices.getListFireStations2().size() - 1).getAddress());
    }
    @Test
    public void deleteFireStationTestOk(){
        FireStation fireStation = new FireStation();
        fireStation.setStation(10);
        fireStation.setAddress("789 boulevard gambetta");
        dataServices.addFireStation(fireStation);
        dataServices.deleteFireStation(fireStation);
        assertEquals(false,dataServices.getListFireStations2().contains(fireStation));
    }
    @Test
    public void deleteFireStationTestNotOk(){
        FireStation fireStation = new FireStation();
        fireStation.setStation(10);
        fireStation.setAddress("789 boulevard gambetta");
        dataServices.addFireStation(fireStation);
        assertEquals(true,dataServices.getListFireStations2().contains(fireStation));
    }
    @Test
    public void updateFireStationTest(){
        FireStation fireStation = new FireStation();
        fireStation.setStation(10);
        fireStation.setAddress("789 boulevard gambetta");
        dataServices.addFireStation(fireStation);
        fireStation.setStation(20);
        dataServices.updateFireStation(fireStation);
        assertEquals(20,dataServices.getListFireStations2().get(dataServices.getListFireStations2().size() - 1).getStation());
    }

    @Test
    public void addMedicalRecordTest(){
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("Jean-Baptiste");
        medicalRecord.setLastName("Champetier");
        medicalRecord.setBirthdate("02/06/2000");
        medicalRecord.setMedications(Arrays.asList(new String[]{"medoc1", "medoc2"}));
        medicalRecord.setAllergies(Arrays.asList(new String[]{"allergies1", "allergies2"}));
        dataServices.addMedicalRecord(medicalRecord);
        assertEquals("Jean-Baptiste",dataServices.getListMedicalRecords2().get(dataServices.getListMedicalRecords2().size() - 1).getFirstName());
    }
    @Test
    public void deleteMedicalRecordTestOk(){
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("Jean-Baptiste");
        medicalRecord.setLastName("Champetier");
        medicalRecord.setBirthdate("02/06/2000");
        medicalRecord.setMedications(Arrays.asList(new String[]{"medoc1", "medoc2"}));
        medicalRecord.setAllergies(Arrays.asList(new String[]{"allergies1", "allergies2"}));
        dataServices.addMedicalRecord(medicalRecord);
        dataServices.deleteMedicalRecord(medicalRecord.getFirstName(),medicalRecord.getLastName());
        assertEquals(false,dataServices.getListMedicalRecords2().contains(medicalRecord));
    }
    @Test
    public void deleteMedicalRecordTestNotOk(){
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("Jean-Baptiste");
        medicalRecord.setLastName("Champetier");
        medicalRecord.setBirthdate("02/06/2000");
        medicalRecord.setMedications(Arrays.asList(new String[]{"medoc1", "medoc2"}));
        medicalRecord.setAllergies(Arrays.asList(new String[]{"allergies1", "allergies2"}));
        dataServices.addMedicalRecord(medicalRecord);
        assertEquals(true,dataServices.getListMedicalRecords2().contains(medicalRecord));
    }
    @Test
    public void updateMedicalRecordTest(){
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("Jean-Baptiste");
        medicalRecord.setLastName("Champetier");
        medicalRecord.setBirthdate("02/06/2000");
        medicalRecord.setMedications(Arrays.asList(new String[]{"medoc1", "medoc2"}));
        medicalRecord.setAllergies(Arrays.asList(new String[]{"allergies1", "allergies2"}));
        dataServices.addMedicalRecord(medicalRecord);
        medicalRecord.setMedications(Arrays.asList(new String[]{"medoc3", "medoc4"}));
        dataServices.updateMedicalRecord(medicalRecord);
        assertEquals(Arrays.asList(new String[]{"medoc3", "medoc4"}),dataServices.getListMedicalRecords2().get(dataServices.getListMedicalRecords2().size() - 1).getMedications());
    }

    @Test
    public void getListPersons2Test(){
        assertEquals(23,dataServices.getListPersons2().size());
    }
    @Test
    public void aPersonTest(){
        addPersonTest();
        List<Person> person = dataServices.aPerson("Jean-Baptiste","Champetier");
        assertEquals("Paris",person.get(0).getCity());
        assertEquals("0610803898",person.get(0).getPhoneNumber());
    }
    //A FINIR
    @Test
    public void personsFromFireStationsTest(){
        List<Person> listPersonsFromFireStations = new ArrayList<>();
        assertEquals(listPersonsFromFireStations,dataServices.personsFromFireStations(Arrays.asList(new String[]{"1", "2"})));
    }
}
