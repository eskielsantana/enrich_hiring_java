package com.services.Document;

import com.manager.Vehicle.VehicleDO;
import com.util.CSVUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {
    private static final Logger LOGGER = Logger.getLogger(DocumentServiceImpl.class);

    private static String CSV_FILE_NAME = "dailyVehicleReport.cvs";

    @Override
    public Optional<File> generateDailyVehiclesReport(List<VehicleDO> vehicleList) {

        List<String[]> data = vehicleList.stream().map(VehicleDO::retrieveValues).collect(Collectors.toList());

        File csvOutputFile = new File(CSV_FILE_NAME);

        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            data.stream()
                     .map(CSVUtil::convertToCSV)
                     .forEach(pw::println);
        } catch (FileNotFoundException e) {
            LOGGER.error("FileNotFoundException while generating the dailyVehiclesReport: " + e.getCause());
            return Optional.empty();
        }

        return Optional.of(csvOutputFile);
    }
}
