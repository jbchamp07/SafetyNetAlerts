package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.service.FireStationServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public class FireStationController {

    final FireStationServices fireStationServices;

    public FireStationController(FireStationServices fireStationServices) {
        this.fireStationServices = fireStationServices;
    }

    @PostMapping("/firestation/{address}/{station}")
    public String fireStationPost(@PathVariable String address, @PathVariable int station) {
        fireStationServices.addAFireStation(address,station);
        return "fireStation added";
    }
    @DeleteMapping("/firestation/{address}/{station}")
    public String fireStationDelete(@PathVariable String address, @PathVariable int station) {
        fireStationServices.deleteAFireStation(address,station);
        return address + " " + station + " deleted";
    }
    @PutMapping("/firestation/{address}/{station}")
    public String fireStationPut(@PathVariable String address, @PathVariable int station) {
        fireStationServices.updateAFireStation(address,station);
        return address + " " + station + " updated";
    }

}
