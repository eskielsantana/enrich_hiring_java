package com.infrastructure.Vehicle;

import com.infrastructure.Insurance.InsuranceDO;
import com.google.auto.value.AutoValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AutoValue
public abstract class VehicleDO {

    public abstract String vehicleRegistration();
    public abstract String make();
    public abstract String model();
    public abstract Integer year();
    public abstract Optional<String> description();
    public abstract InsuranceDO insurance();

    public static Builder builder() {
        return new AutoValue_VehicleDO.Builder();
    }

    public static VehicleDO fromEntity(Vehicle vehicle) {
        return VehicleDO.create(vehicle.getVehicleRegistration(), vehicle.getMake(), vehicle.getModel(), vehicle.getYear(),
                                vehicle.getDescription(), InsuranceDO.fromEntity(vehicle.getInsurance()));
    }

    public String[] retrieveValues(){
        List<String> vehicleValues = Arrays.asList(vehicleRegistration(), make(), model(), String.valueOf(year()),
                                                   description().orElse("EMPTY"));

        List<String> result = new ArrayList<>(vehicleValues);

        result.addAll(insurance().retrieveValues());

        return result.toArray(new String[0]);
    }

    public static VehicleDO create(String vehicleRegistration,
                                   String make,
                                   String model,
                                   Integer year,
                                   Optional<String> description,
                                   InsuranceDO insurance) {
        return builder()
                .vehicleRegistration(vehicleRegistration)
                .make(make)
                .model(model)
                .year(year)
                .description(description)
                .insurance(insurance)
                .build();
    }


    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder vehicleRegistration(String vehicleRegistration);

        public abstract Builder model(String model);

        public abstract Builder year(Integer year);

        public abstract Builder insurance(InsuranceDO insurance);

        public abstract Builder description(Optional<String> description);

        public abstract Builder make(String make);

        public abstract VehicleDO build();
    }
}
