package com.domain.vehicle;

import com.google.auto.value.AutoValue;
import com.infrastructure.insurance.Insurance;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static com.util.DateUtil.convertInstantToFormattedDate;

@AutoValue
public abstract class VehicleInsurance {
    public abstract String policyNumber();
    public abstract Instant startDate();
    public abstract Instant endDate();

    public static VehicleInsurance create(String policyNumber, Instant startDate, Instant endDate) {
        return builder()
                .policyNumber(policyNumber)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }

    public static Builder builder() {
        return new AutoValue_VehicleInsurance.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder policyNumber(String policyNumber);
        public abstract Builder startDate(Instant startDate);
        public abstract Builder endDate(Instant endDate);
        public abstract VehicleInsurance build();
    }

    public List<String> retrieveValues(){
        return Arrays.asList(policyNumber(), convertInstantToFormattedDate(startDate()), convertInstantToFormattedDate(endDate()));
    }

    public static VehicleInsurance fromEntity(Insurance insurance) {
        return VehicleInsurance.create(insurance.getPolicyNumber(),
                                       insurance.getStartDate(),
                                       insurance.getEndDate());
    }
}
