package com.openclassrooms.SafetyNetAlerts.controllerTest;

import com.openclassrooms.SafetyNetAlerts.service.MedicalRecordServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MedicalRecordServices medicalRecordServices;
    private MvcResult mvcResult;
    private String result;

    @Test
    public void medicalRecordPostTest() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/medicalRecord")).andReturn();
        result = mvcResult.getResponse().getContentAsString();
        assertEquals("medicalRecord added",result);
    }

    @Test
    public void medicalRecordDeleteTest() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/medicalRecord?firstName=jb&lastName=champ")).andReturn();
        result = mvcResult.getResponse().getContentAsString();
        assertEquals("jb champ deleted",result);
    }

    @Test
    public void medicalRecordPutTest() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/medicalRecord")).andReturn();
        result = mvcResult.getResponse().getContentAsString();
        assertEquals("null null updated",result);
    }

}
