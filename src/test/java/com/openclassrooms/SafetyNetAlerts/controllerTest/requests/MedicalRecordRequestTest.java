package com.openclassrooms.SafetyNetAlerts.controllerTest.requests;

import com.openclassrooms.SafetyNetAlerts.controller.requests.MedicalRecordRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MedicalRecordRequestTest {

    MedicalRecordRequest medicalRecordRequest;

    @Test
    public void equalsTest(){
        medicalRecordRequest = new MedicalRecordRequest();
        medicalRecordRequest.equals("a");
        medicalRecordRequest.equals(null);
    }

    @Test
    public void hashCodeTest(){
        medicalRecordRequest = new MedicalRecordRequest();
        medicalRecordRequest.hashCode();
    }
    @Test
    public void toStringTest(){
        medicalRecordRequest = new MedicalRecordRequest();
        medicalRecordRequest.toString();
    }
}
