package com.openclassrooms.SafetyNetAlerts.controllerTest;

import com.openclassrooms.SafetyNetAlerts.dto.AddressDTO;
import com.openclassrooms.SafetyNetAlerts.dto.PersonDTOFire;
import com.openclassrooms.SafetyNetAlerts.dto.PersonDTOFireStation;
import com.openclassrooms.SafetyNetAlerts.dto.PersonDTOPersonInfo;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.service.PersonServices;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@WebMvcTest(controllers = MainController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PersonServices personServices;
    private MvcResult mvcResult;
    private String result;
    @Before
    public void init(){
    }

    @Test
    public void shouldCreateMockMvc(){
        assertNotNull(mockMvc);
    }

    @Test
    public void listPersonOfAFireStationTest() throws Exception {
        HashMap m = new HashMap();
        List<PersonDTOFireStation> list = new ArrayList<>();
        for(int i = 0; i < 4;i++){
            list.add(new PersonDTOFireStation());
        }
        m.put("persons",list);
        m.put("Numbers of kids",0);
        m.put("Numbers of adults",4);
        when(personServices.findPersonByFireStation(1)).thenReturn(m);
        mockMvc.perform(MockMvcRequestBuilders.get("/firestation").param("stationNumber", String.valueOf(1))).andExpect(MockMvcResultMatchers.status().isOk());
        //HashMap test = mockMvc.perform(MockMvcRequestBuilders.get("/firestation").param("stationNumber", String.valueOf(1))).andExpect();
        //MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/firestation").param("stationNumber", String.valueOf(1))).andReturn();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/firestation?stationNumber=1")).andReturn();
        result = mvcResult.getResponse().getContentAsString();
        assertEquals(true, result.contains("Numbers of kids\":0"));
        assertEquals(true, result.contains("Numbers of adults\":4"));
        /*Type listOfMyClassObject = new TypeToken<ArrayList<PersonDTOFireStation>>() {}.getType();
        Gson g = new Gson();
        List<PersonDTOFireStation> listP = g.fromJson(s,listOfMyClassObject);*/
    }

    @Test
    public void childAlertTest() throws Exception {
        HashMap<String,List<Person>> m = new HashMap<>();
        List<Person> listPerson = new ArrayList<>();
        Person p = new Person();
        p.setFirstName("jb");
        p.setLastName("champ");
        listPerson.add(p);
        m.put("kids",listPerson);
        m.put("adults",null);
        when(personServices.kidsOfAHouse("ad")).thenReturn(m);
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/childAlert?address=ad")).andReturn();
        result = mvcResult.getResponse().getContentAsString();
        assertEquals(true, result.contains("adults\":null"));
        assertEquals(true, result.contains("kids\":[{\"firstName\":\"jb\""));
    }

    @Test
    public void phoneAlertTest() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("num1");
        list.add("num2");
        when(personServices.findPhoneByFireStation(1)).thenReturn(list);
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/phoneAlert?firestation=1")).andReturn();
        result = mvcResult.getResponse().getContentAsString();
        assertEquals(true, result.contains("[\"num1\",\"num2\"]"));
    }

    @Test
    public void fireTest() throws Exception {
        HashMap m = new HashMap<>();
        PersonDTOFire p = new PersonDTOFire();
        p.setLastName("champ");
        List<PersonDTOFire> listPerson = new ArrayList<>();
        listPerson.add(p);
        m.put("persons",p);
        when(personServices.findPersonByAddress("ad")).thenReturn(m);
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/fire?address=ad")).andReturn();
        result = mvcResult.getResponse().getContentAsString();
        assertEquals(true, result.contains("{\"persons\":{\"lastName\":\"champ\",\"phone\":null,\"age\":0,\"medicalHistory\":null}}"));
    }


    //TODO
    @Test
    public void listStationsTest() throws Exception {
        /*List toReturn = new ArrayList();
        toReturn.add(1);
        List<AddressDTO> listStation = new ArrayList();
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddress("ad1");
        listStation.add(addressDTO);
        when(personServices.personsFromFireStations(toReturn)).thenReturn(listStation);
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("//flood/stations?listStations=1")).andReturn();
        result = mvcResult.getResponse().getContentAsString();
        assertEquals(false, result.contains("adults\":null"));*/

        mockMvc.perform(MockMvcRequestBuilders.get("/flood/stations").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"list\": [1, 2]\n" +
                "}").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        mvcResult =  mockMvc.perform(MockMvcRequestBuilders.get("/flood/stations").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"list\": [1, 2]\n" +
                "}").accept(MediaType.APPLICATION_JSON)).andReturn();
        result = mvcResult.getResponse().getContentAsString();
        assertEquals("[]",result);
    }

    @Test
    public void personInfoTest() throws Exception {
        List<PersonDTOPersonInfo> list = new ArrayList<>();
        PersonDTOPersonInfo p = new PersonDTOPersonInfo();
        p.setEmail("gmail.com");
        list.add(p);
        when(personServices.aPerson("jb","champ")).thenReturn(list);
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/personInfo?firstName=jb&lastName=champ")).andReturn();
        result = mvcResult.getResponse().getContentAsString();
        assertEquals(true, result.contains("[{\"lastName\":null,\"address\":null,\"age\":0,\"email\":\"gmail.com\",\"medicalHistory\":null}]"));
    }

    @Test
    public void listStationsEmailTest() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("email1");
        list.add("email2");
        when(personServices.findEmailByCity("paris")).thenReturn(list);
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/communityEmail?city=paris")).andReturn();
        result = mvcResult.getResponse().getContentAsString();
        assertEquals(true, result.contains("[\"email1\",\"email2\"]"));
    }
}
