package com.openclassrooms.SafetyNetAlerts.service;

import org.springframework.stereotype.Service;

@Service
public class FireStationServices {

    private final DataServices dataServices;

    public FireStationServices(DataServices dataServices) {
        this.dataServices = dataServices;
    }

    public void addAFireStation(String address, int station) {
        dataServices.addFireStation(address,station);
    }

    public void deleteAFireStation(String address, int station) {
        dataServices.deleteFireStation(address,station);
    }

    public void updateAFireStation(String address, int station) {
        dataServices.updateFireStation(address,station);
    }
}
