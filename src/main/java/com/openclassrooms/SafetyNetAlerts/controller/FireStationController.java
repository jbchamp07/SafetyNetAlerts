package com.openclassrooms.SafetyNetAlerts.controller;

import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import com.openclassrooms.SafetyNetAlerts.service.FireStationServices;
import org.springframework.web.bind.annotation.*;

/**
 * The type Fire station controller.
 */
@RestController
public class FireStationController {

    /**
     * The Fire station services.
     */
    final FireStationServices fireStationServices;

    /**
     * Instantiates a new Fire station controller.
     *
     * @param fireStationServices the fire station services
     */
    public FireStationController(FireStationServices fireStationServices) {
        this.fireStationServices = fireStationServices;
    }

    /**
     * Fire station post string.
     *
     * @param fireStation the fire station
     * @return the string
     */
    @PostMapping("/firestation")
    public String fireStationPost(FireStation fireStation) {
        fireStationServices.addAFireStation(fireStation);
        return "fireStation added";
    }

    /**
     * Fire station delete string.
     *
     * @param fireStation the fire station
     * @return the string
     */
    @DeleteMapping("/firestation")
    public String fireStationDelete(FireStation fireStation) {
        fireStationServices.deleteAFireStation(fireStation);
        return fireStation.getAddress() + " " + fireStation.getStation() + " deleted";
    }

    /**
     * Fire station put string.
     *
     * @param fireStation the fire station
     * @return the string
     */
    @PutMapping("/firestation")
    public String fireStationPut(FireStation fireStation) {
        fireStationServices.updateAFireStation(fireStation);
        return fireStation.getAddress() + " " + fireStation.getStation() + " updated";
    }

}
