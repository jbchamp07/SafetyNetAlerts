package com.openclassrooms.SafetyNetAlerts.controllerTest.requests;

import com.openclassrooms.SafetyNetAlerts.controller.requests.ListStationsRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ListStationsRequestTest {

    ListStationsRequest listStationsRequest;

    @Test
    public void equalsTest(){
        listStationsRequest = new ListStationsRequest();
        listStationsRequest.equals("a");
        listStationsRequest.equals(null);
    }

    @Test
    public void hashCodeTest(){
        listStationsRequest = new ListStationsRequest();
        listStationsRequest.hashCode();
    }
    @Test
    public void toStringTest(){
        listStationsRequest = new ListStationsRequest();
        listStationsRequest.toString();
    }

}
