package com.domain.report;

import com.domain.vehicle.Vehicle;
import com.domain.vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    public VehicleService vehicleService;
    @Autowired
    public FileManager fileManager;

    public void generateDailyVehiclesReport() {
        List<Vehicle> vehicles = vehicleService.getRegisteredVehicles();

        if (vehicles.isEmpty()) {
            return;
        }

        List<String[]> data = vehicles.stream().map(Vehicle::retrieveValues).toList();

        fileManager.export(data);
    }
}
