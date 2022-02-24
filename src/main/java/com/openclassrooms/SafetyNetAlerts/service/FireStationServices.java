package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import org.springframework.stereotype.Service;

@Service
public class FireStationServices {

    private final DataServices dataServices;

    public FireStationServices(DataServices dataServices) {
        this.dataServices = dataServices;
    }

    public void addAFireStation(FireStation fireStation) {
        dataServices.addFireStation(fireStation);
    }

    public void deleteAFireStation(FireStation fireStation) {
        dataServices.deleteFireStation(fireStation);
    }

    public void updateAFireStation(FireStation fireStation) {
        dataServices.updateFireStation(fireStation);
    }
}
