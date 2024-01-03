package domain.report;

import domain.upload.UploadService;
import domain.vehicle.Vehicle;
import domain.vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService {
    @Autowired
    public VehicleService vehicleService;
    @Autowired
    public FileManager fileManager;

    @Autowired
    public UploadService uploadService;

    @PostConstruct
    public void postConstruct() {
        generateDailyVehiclesReport();
    }

    public void generateDailyVehiclesReport() {
        List<Vehicle> vehicles = vehicleService.getRegisteredVehicles();

        if (vehicles.isEmpty()) { return; }

        List<String[]> data = vehicles.stream().map(Vehicle::retrieveValues).collect(Collectors.toList());

        Optional<File> resultFile = fileManager.export(data);

        resultFile.ifPresent(uploadService::uploadReport);
    }
}
