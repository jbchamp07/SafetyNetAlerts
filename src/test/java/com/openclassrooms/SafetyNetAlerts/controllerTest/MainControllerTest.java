package com.openclassrooms.SafetyNetAlerts.controllerTest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.openclassrooms.SafetyNetAlerts.controller.MainController;
import com.openclassrooms.SafetyNetAlerts.dto.AddressDTO;
import com.openclassrooms.SafetyNetAlerts.dto.PersonDTOFire;
import com.openclassrooms.SafetyNetAlerts.dto.PersonDTOFireStation;
import com.openclassrooms.SafetyNetAlerts.dto.PersonDTOPersonInfo;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.service.PersonServices;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


//@WebMvcTest(controllers = MainController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PersonServices personServices;

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
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/firestation?stationNumber=1")).andReturn();
        String s = mvcResult.getResponse().getContentAsString();
        assertEquals(true,s.contains("Numbers of kids\":0"));
        assertEquals(true,s.contains("Numbers of adults\":4"));
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
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/childAlert?address=ad")).andReturn();
        String s = mvcResult.getResponse().getContentAsString();
        assertEquals(true,s.contains("adults\":null"));
        assertEquals(true,s.contains("kids\":[{\"firstName\":\"jb\""));
    }

    @Test
    public void phoneAlertTest() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("num1");
        list.add("num2");
        when(personServices.findPhoneByFireStation(1)).thenReturn(list);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/phoneAlert?firestation=1")).andReturn();
        String s = mvcResult.getResponse().getContentAsString();
        assertEquals(true,s.contains("[\"num1\",\"num2\"]"));
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
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/fire?address=ad")).andReturn();
        String s = mvcResult.getResponse().getContentAsString();
        assertEquals(true,s.contains("{\"persons\":{\"lastName\":\"champ\",\"phone\":null,\"age\":0,\"medicalHistory\":null}}"));
    }


    //TODO
    @Test
    public void listStationsTest() throws Exception {
        List toReturn = new ArrayList();
        toReturn.add(1);
        List<AddressDTO> listStation = new ArrayList();
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddress("ad1");
        listStation.add(addressDTO);
        when(personServices.personsFromFireStations(null)).thenReturn(listStation);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("//flood/stations?listStations=null")).andReturn();
        String s = mvcResult.getResponse().getContentAsString();
        assertEquals(true,s.contains("adults\":null"));
    }

    @Test
    public void personInfoTest() throws Exception {
        List<PersonDTOPersonInfo> list = new ArrayList<>();
        PersonDTOPersonInfo p = new PersonDTOPersonInfo();
        p.setEmail("gmail.com");
        list.add(p);
        when(personServices.aPerson("jb","champ")).thenReturn(list);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/personInfo?firstName=jb&lastName=champ")).andReturn();
        String s = mvcResult.getResponse().getContentAsString();
        assertEquals(true,s.contains("[{\"lastName\":null,\"address\":null,\"age\":0,\"email\":\"gmail.com\",\"medicalHistory\":null}]"));
    }

    @Test
    public void listStationsEmailTest() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("email1");
        list.add("email2");
        when(personServices.findEmailByCity("paris")).thenReturn(list);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/communityEmail?city=paris")).andReturn();
        String s = mvcResult.getResponse().getContentAsString();
        assertEquals(true,s.contains("[\"email1\",\"email2\"]"));
    }
}
