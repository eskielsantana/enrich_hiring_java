package com.domain.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    @Autowired
    VehicleManager vehicleManager;

    public List<Vehicle> getAllRegisteredVehicles() {
        return vehicleManager.retrieveAllVehicles().stream().map(Vehicle::fromEntity).collect(Collectors.toList());
    }
}
