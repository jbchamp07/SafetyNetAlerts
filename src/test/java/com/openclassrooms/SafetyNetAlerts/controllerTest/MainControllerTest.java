package com.openclassrooms.SafetyNetAlerts.controllerTest;

import com.openclassrooms.SafetyNetAlerts.controller.MainController;
import com.openclassrooms.SafetyNetAlerts.dto.PersonDTOFireStation;
import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.service.PersonServices;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        when(personServices.findPersonByFireStation(4)).thenReturn(m);
        mockMvc.perform(MockMvcRequestBuilders.get("/firestation").param("stationNumber", String.valueOf(1))).andExpect(MockMvcResultMatchers.status().isOk());
        //HashMap test = mockMvc.perform(MockMvcRequestBuilders.get("/firestation").param("stationNumber", String.valueOf(1))).andExpect();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/firestation").param("stationNumber", String.valueOf(1))).andReturn();
        Collection<String> s = mvcResult.getResponse().getHeaderNames();
        assertEquals("{}",mvcResult.getResponse().getContentAsString());
    }

}
