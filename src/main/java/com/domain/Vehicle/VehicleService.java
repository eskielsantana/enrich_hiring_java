package com.domain.Vehicle;

import com.infrastructure.Vehicle.VehicleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    @Autowired
    VehicleManager vehicleManager;

    public List<VehicleDO> getAllRegisteredVehicles() {
        return vehicleManager.retrieveAllVehicles().stream().map(VehicleDO::fromEntity).collect(Collectors.toList());
    }
}
