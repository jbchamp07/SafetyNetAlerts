package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import com.openclassrooms.SafetyNetAlerts.service.FireStationServices;
import org.springframework.web.bind.annotation.*;

@RestController
public class FireStationController {

    final FireStationServices fireStationServices;

    public FireStationController(FireStationServices fireStationServices) {
        this.fireStationServices = fireStationServices;
    }

    @PostMapping("/firestation")
    public String fireStationPost(FireStation fireStation) {
        fireStationServices.addAFireStation(fireStation);
        return "fireStation added";
    }
    @DeleteMapping("/firestation")
    public String fireStationDelete(FireStation fireStation) {
        fireStationServices.deleteAFireStation(fireStation);
        return fireStation.getAddress() + " " + fireStation.getStation() + " deleted";
    }
    @PutMapping("/firestation")
    public String fireStationPut(FireStation fireStation) {
        fireStationServices.updateAFireStation(fireStation);
        return fireStation.getAddress() + " " + fireStation.getStation() + " updated";
    }

}
