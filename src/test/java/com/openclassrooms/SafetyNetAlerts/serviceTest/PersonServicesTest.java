package com.openclassrooms.SafetyNetAlerts.serviceTest;

import com.openclassrooms.SafetyNetAlerts.dto.PersonDTOFire;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.service.DataServices;
import com.openclassrooms.SafetyNetAlerts.service.IDataServices;
import com.openclassrooms.SafetyNetAlerts.service.PersonServices;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//@ActiveProfiles
//@ExtendWith(MockitoExtension.class)
@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
public class PersonServicesTest {

    @Autowired
    private PersonServices personServices;

    @Test
    public void findPersonByAddressTestOk() {
        HashMap m = personServices.findPersonByAddress("29 15th St");
        List<PersonDTOFire> l = (List<PersonDTOFire>) m.get("persons");
        assertEquals("Marrack",l.get(0).getLastName());
    }
    @Test
    public void findPersonByAddressTestNotOk() {
        HashMap m = personServices.findPersonByAddress("");
        List<PersonDTOFire> l = (List<PersonDTOFire>) m.get("persons");
        assertEquals(0,l.size());
    }
    @Test
    public void findPhoneByFireStationTestOk() {
        assertEquals(4,personServices.findPhoneByFireStation(1).size());
    }
    @Test
    public void findPhoneByFireStationTestNotOk() {
        assertEquals(0,personServices.findPhoneByFireStation(10).size());
    }
    @Test
    public void findEmailByCityTestNotOk() {
        assertEquals(0,personServices.findEmailByCity("").size());
    }
    @Test
    public void findEmailByCityTestOk() {
        assertEquals(15,personServices.findEmailByCity("Culver").size());
    }
    @Test
    public void findPersonByFireStationTest() {
        assertEquals(3,personServices.findPersonByFireStation(1).size());
    }
    @Test
    public void kidsOfAHouseTest() {
        assertEquals(2,personServices.kidsOfAHouse("1509 Culver St").size());
    }
    @Test
    public void aPersonTest() {
        assertEquals(1,personServices.aPerson("John","Boyd").size());
    }
    @Test
    public void personsFromFireStationsTest() {
        assertEquals(3,personServices.personsFromFireStations(Collections.singletonList(1)).size());
    }
/*

    @MockBean
    private IDataServices dataServices;

    @Before
    public void init(){
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void findPersonByAddressTestOk() throws IOException, ParseException {
        when(dataServices.findPersonByAddress("1")).thenReturn(new HashMap<>());
        assertEquals( null,personServices.findPersonByAddress("1"));
    }
    @Test
    public void findPersonByAddressTestNotOk(){
        when(dataServices.findPersonByAddress("2")).thenReturn(null);
        assertEquals( null,personServices.findPersonByAddress("2"));
    }
*/
}
