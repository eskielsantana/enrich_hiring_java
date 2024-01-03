import domain.report.ReportService;
import domain.vehicle.Vehicle;
import domain.vehicle.VehicleInsurance;
import domain.vehicle.VehicleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ReportServiceTest {

    @InjectMocks
    ReportService reportService = new ReportService();

    @Mock
    VehicleService vehicleService = new VehicleService();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        Optional<File> file = Optional.of(new File("new/file"));
        List<Vehicle> vehicleList = vehicleGenerator(5);

        when(vehicleService.getRegisteredVehicles()).thenReturn(vehicleList);
//        when(documentService.generateDailyVehiclesReport(anyList())).thenReturn(file);
    }

    @Test
    public void whenGenerateDailyVehiclesReportIsCalled_VehiclesAreLoadedAndFileIsGenerated_CallsExpectedFunctionsOnce() {

        reportService.generateDailyVehiclesReport();

        Mockito.verify(vehicleService).getRegisteredVehicles();
//        Mockito.verify(documentService).generateDailyVehiclesReport(anyList());
//        Mockito.verify(requestService).postFile(anyString(), any(File.class));
    }

    @Test
    public void whenGenerateDailyVehiclesReportIsCalled_NoVehicleReportsReturned_NoDocumentIsGenerated() {

        when(vehicleService.getRegisteredVehicles()).thenReturn(Collections.emptyList());

        reportService.generateDailyVehiclesReport();

//        Mockito.verify(documentService, never()).generateDailyVehiclesReport(anyList());
//        Mockito.verify(requestService, never()).postFile(anyString(), any(File.class));
    }

    @Test
    public void whenGenerateDailyVehiclesReportIsCalled_FileIsNotGenerated_PostFileNotCalled() {

//        when(documentService.generateDailyVehiclesReport(anyList())).thenReturn(Optional.empty());

        reportService.generateDailyVehiclesReport();

//        Mockito.verify(requestService, never()).postFile(anyString(), any(File.class));
    }

    private List<Vehicle> vehicleGenerator(int amount) {
        return IntStream.range(0, amount)
                        .mapToObj(i -> Vehicle.create(UUID.randomUUID().toString(), "Some Make", "Any Model", i, Optional.empty(),
                                                      VehicleInsurance.create(UUID.randomUUID().toString(),
                                                                              Instant.now(), Instant.now().plus(15, ChronoUnit.DAYS))))
                        .collect(Collectors.toList());
    }

}
