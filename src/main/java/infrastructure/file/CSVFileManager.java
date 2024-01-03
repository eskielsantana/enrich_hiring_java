package infrastructure.file;

import com.opencsv.CSVWriter;
import domain.report.FileManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CSVFileManager implements FileManager {
    private static final Logger LOGGER = Logger.getLogger(CSVFileManager.class);
    private static final String FILE_NAME = "dailyVehicleReport.cvs";

    @Override
    public Optional<File> export(List<String[]> data) {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(FILE_NAME))) {
            csvWriter.writeAll(data);
            return Optional.of(new File(FILE_NAME));
        } catch (IOException e) {
            LOGGER.error("FileNotFoundException while generating the csv file: " + e.getCause());
        }
        return Optional.empty();
    }
}
