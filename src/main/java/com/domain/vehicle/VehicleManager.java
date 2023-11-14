package com.domain.vehicle;

import com.infrastructure.vehicle.Vehicle;

import java.util.List;

public interface VehicleManager {
    List<Vehicle> retrieveAllVehicles();
}
