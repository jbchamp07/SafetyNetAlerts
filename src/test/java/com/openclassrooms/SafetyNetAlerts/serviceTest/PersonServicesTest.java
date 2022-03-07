package com.openclassrooms.SafetyNetAlerts.serviceTest;

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
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//@ActiveProfiles
@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
public class PersonServicesTest {
/*
    @Autowired
    private PersonServices personServices;

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
