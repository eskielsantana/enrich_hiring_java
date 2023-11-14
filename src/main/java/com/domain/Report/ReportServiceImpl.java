package com.domain.Report;

import com.infrastructure.Vehicle.VehicleDO;
import com.domain.Document.DocumentService;
import com.domain.Request.RequestService;
import com.domain.Vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    VehicleService vehicleService;

    @Autowired
    DocumentService documentService;

    @Autowired
    RequestService requestService;

    @Override
    public void generateDailyVehiclesReport() {
        List<VehicleDO> vehicles = vehicleService.getAllRegisteredVehicles();

        if(vehicles.isEmpty()) return;

        Optional<File> report = documentService.generateDailyVehiclesReport(vehicles);

        report.ifPresent(file -> requestService.postFile("https://eskiel.free.beeceptor.com/test", file));
    }
}
