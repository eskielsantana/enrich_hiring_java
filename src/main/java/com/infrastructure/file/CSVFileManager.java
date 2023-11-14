package com.infrastructure.file;

import com.domain.document.DocumentService;
import com.domain.report.FileManager;
import com.opencsv.CSVWriter;
import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVFileManager implements FileManager {
    private static final Logger LOGGER = Logger.getLogger(DocumentService.class);
    private final static String FILE_NAME = "dailyVehicleReport.cvs";

    @Override
    public void export(List<String[]> data) {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(FILE_NAME))) {
            csvWriter.writeAll(data);
        } catch (IOException e) {
            LOGGER.error("FileNotFoundException while generating the csv file: " + e.getCause());
        }
    }
}
