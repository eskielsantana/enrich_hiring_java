package com.domain.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    @Autowired
    public VehicleManager vehicleManager;

    public List<Vehicle> getRegisteredVehicles() {
        return vehicleManager.retrieveAllVehicles().stream().map(Vehicle::fromEntity).toList();
    }
}
