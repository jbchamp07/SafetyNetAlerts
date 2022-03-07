package com.openclassrooms.SafetyNetAlerts.controllerTest;

import com.openclassrooms.SafetyNetAlerts.service.PersonServices;
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
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PersonServices personServices;
    private MvcResult mvcResult;
    private String result;

    @Test
    public void personPostTest() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/person")).andReturn();
        result = mvcResult.getResponse().getContentAsString();
        assertEquals("person added",result);
    }

    @Test
    public void personDeleteTest() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/person?firstName=jb&lastName=champ")).andReturn();
        result = mvcResult.getResponse().getContentAsString();
        assertEquals("jb champ deleted",result);
    }

    @Test
    public void personPutTest() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/person")).andReturn();
        result = mvcResult.getResponse().getContentAsString();
        assertEquals("null null updated",result);
    }

}
