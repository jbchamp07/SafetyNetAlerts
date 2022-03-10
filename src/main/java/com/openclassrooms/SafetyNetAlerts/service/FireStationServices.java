package com.openclassrooms.SafetyNetAlerts.service;

import com.openclassrooms.SafetyNetAlerts.model.FireStation;
import org.springframework.stereotype.Service;

/**
 * The type Fire station services.
 */
@Service
public class FireStationServices {

    private final IDataServices dataServices;

    /**
     * Instantiates a new Fire station services.
     *
     * @param dataServices the data services
     */
    public FireStationServices(DataServices dataServices) {
        this.dataServices = dataServices;
    }

    /**
     * Add a fire station.
     *
     * @param fireStation the fire station
     */
    public void addAFireStation(FireStation fireStation) {
        dataServices.addFireStation(fireStation);
    }

    /**
     * Delete a fire station.
     *
     * @param fireStation the fire station
     */
    public void deleteAFireStation(FireStation fireStation) {
        dataServices.deleteFireStation(fireStation);
    }

    /**
     * Update a fire station.
     *
     * @param fireStation the fire station
     */
    public void updateAFireStation(FireStation fireStation) {
        dataServices.updateFireStation(fireStation);
    }
}
