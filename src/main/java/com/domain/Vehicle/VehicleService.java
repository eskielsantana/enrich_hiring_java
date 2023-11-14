package com.domain.Vehicle;

import com.infrastructure.Vehicle.VehicleDO;

import java.util.List;

public interface VehicleService {

    List<VehicleDO> getAllRegisteredVehicles();

}
