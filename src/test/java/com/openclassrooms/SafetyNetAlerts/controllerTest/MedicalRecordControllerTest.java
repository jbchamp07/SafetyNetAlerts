package com.openclassrooms.SafetyNetAlerts.controllerTest;

import com.openclassrooms.SafetyNetAlerts.service.MedicalRecordServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MedicalRecordServices medicalRecordServices;

    @Test
    public void medicalRecordPostTest(){

    }

    @Test
    public void medicalRecordDeleteTest(){

    }

    @Test
    public void medicalRecordPutTest(){

    }

}
