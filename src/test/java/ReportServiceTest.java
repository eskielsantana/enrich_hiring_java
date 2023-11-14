import com.infrastructure.Insurance.InsuranceDO;
import com.infrastructure.Vehicle.VehicleDO;
import com.domain.Document.DocumentService;
import com.domain.Document.DocumentServiceImpl;
import com.domain.Report.ReportService;
import com.domain.Report.ReportServiceImpl;
import com.domain.Request.RequestService;
import com.domain.Request.RequestServiceImpl;
import com.domain.Vehicle.VehicleService;
import com.domain.Vehicle.VehicleServiceImpl;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ReportServiceTest {

    @InjectMocks
    ReportService reportService = new ReportServiceImpl();

    @Mock
    VehicleService vehicleService = new VehicleServiceImpl();

    @Mock
    DocumentService documentService = new DocumentServiceImpl();

    @Mock
    RequestService requestService = new RequestServiceImpl();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        Optional<File> file = Optional.of(new File("new/file"));
        List<VehicleDO> vehicleList = vehicleGenerator(5);

        when(vehicleService.getAllRegisteredVehicles()).thenReturn(vehicleList);
        when(documentService.generateDailyVehiclesReport(anyList())).thenReturn(file);
    }

    @Test
    public void whenGenerateDailyVehiclesReportIsCalled_VehiclesAreLoadedAndFileIsGenerated_CallsExpectedFunctionsOnce() {

        reportService.generateDailyVehiclesReport();

        Mockito.verify(vehicleService).getAllRegisteredVehicles();
        Mockito.verify(documentService).generateDailyVehiclesReport(anyList());
        Mockito.verify(requestService).postFile(anyString(), any(File.class));
    }

    @Test
    public void whenGenerateDailyVehiclesReportIsCalled_NoVehicleReportsReturned_NoDocumentIsGenerated() {

        when(vehicleService.getAllRegisteredVehicles()).thenReturn(Collections.emptyList());

        reportService.generateDailyVehiclesReport();

        Mockito.verify(documentService, never()).generateDailyVehiclesReport(anyList());
        Mockito.verify(requestService, never()).postFile(anyString(), any(File.class));
    }

    @Test
    public void whenGenerateDailyVehiclesReportIsCalled_FileIsNotGenerated_PostFileNotCalled() {

        when(documentService.generateDailyVehiclesReport(anyList())).thenReturn(Optional.empty());

        reportService.generateDailyVehiclesReport();

        Mockito.verify(requestService, never()).postFile(anyString(), any(File.class));
    }

    private List<VehicleDO> vehicleGenerator(int amount){
        return IntStream.range(0, amount)
                        .mapToObj(i -> VehicleDO.create(UUID.randomUUID().toString(), "Some Make", "Any Model", i, Optional.empty(),
                                                        InsuranceDO.create(UUID.randomUUID().toString(),
                                                                           Instant.now(), Instant.now().plus(15, ChronoUnit.DAYS))))
                        .collect(Collectors.toList());
    }

}
