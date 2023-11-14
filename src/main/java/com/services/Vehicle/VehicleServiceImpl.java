package com.services.Vehicle;

import com.manager.Vehicle.VehicleDO;
import com.manager.Vehicle.VehicleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleManager vehicleManager;

    @Override
    public List<VehicleDO> getAllRegisteredVehicles() {
        return vehicleManager.retrieveAllVehicles().stream().map(VehicleDO::fromEntity).collect(Collectors.toList());
    }
}
