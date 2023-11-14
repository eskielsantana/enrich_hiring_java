package com.domain.scheduled;

import com.domain.report.ReportService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
public class ScheduleService {
    private static final Logger LOGGER = Logger.getLogger(ScheduleService.class);

    @Autowired
    public ReportService reportService;

    // Everyday at midnight
    @Scheduled(cron = "0 0 0 * * ?")
    public void generateDailyReport() {
        Instant startTime = Instant.now();
        reportService.generateDailyVehiclesReport();

        LOGGER.info("generateDailyReport() took " + Duration.between(startTime, Instant.now()).getSeconds() + " seconds to run.");
    }
}
