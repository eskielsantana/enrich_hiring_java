package com.domain.report;

import com.domain.vehicle.Vehicle;
import com.domain.document.DocumentService;
import com.domain.request.RequestService;
import com.domain.vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    VehicleService vehicleService;

    @Autowired
    DocumentService documentService;

    @Autowired
    RequestService requestService;

    public void generateDailyVehiclesReport() {
        List<Vehicle> vehicles = vehicleService.getAllRegisteredVehicles();

        if(vehicles.isEmpty()) return;

        Optional<File> report = documentService.generateDailyVehiclesReport(vehicles);

        report.ifPresent(file -> requestService.postFile("https://eskiel.free.beeceptor.com/test", file));
    }
}
