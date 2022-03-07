package com.openclassrooms.SafetyNetAlerts.controllerTest;

import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import com.openclassrooms.SafetyNetAlerts.service.FireStationServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class FireStationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FireStationServices fireStationServices;
    private MvcResult mvcResult;
    private String result;

    @Test
    public void fireStationPostTest() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/firestation")).andReturn();
        result = mvcResult.getResponse().getContentAsString();
        assertEquals("fireStation added",result);
    }

    @Test
    public void fireStationDeleteTest() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/firestation")).andReturn();
        result = mvcResult.getResponse().getContentAsString();
        assertEquals("null 0 deleted",result);
    }

    @Test
    public void fireStationPutTest() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/firestation")).andReturn();
        result = mvcResult.getResponse().getContentAsString();
        assertEquals("null 0 updated",result);
    }

}
