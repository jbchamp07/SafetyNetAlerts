package com.openclassrooms.SafetyNetAlerts.serviceTest;

import com.openclassrooms.SafetyNetAlerts.model.Person;
import com.openclassrooms.SafetyNetAlerts.service.IDataServices;
import com.openclassrooms.SafetyNetAlerts.service.PersonServices;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration
public class PersonServicesTest {

    @Autowired
    private PersonServices personServices;

    @MockBean
    private IDataServices dataServices;

    @Test
    public void findPersonByAddressTestOk(){
        when(dataServices.findPersonByAddress("1")).thenReturn(new HashMap<>());
        assertEquals( null,personServices.findPersonByAddress("1"));
    }
    @Test
    public void findPersonByAddressTestNotOk(){
        when(dataServices.findPersonByAddress("2")).thenReturn(null);
        assertEquals( null,personServices.findPersonByAddress("2"));
    }

}
