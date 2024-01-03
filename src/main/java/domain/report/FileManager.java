package domain.report;

import java.io.File;
import java.util.List;
import java.util.Optional;

public interface FileManager {
    Optional<File> export(List<String[]> data);
}
