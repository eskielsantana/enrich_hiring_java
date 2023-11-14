package com.services.Document;

import com.manager.Vehicle.VehicleDO;

import java.io.File;
import java.util.List;
import java.util.Optional;

public interface DocumentService {

    Optional<File> generateDailyVehiclesReport(List<VehicleDO> vehicleList);

}
