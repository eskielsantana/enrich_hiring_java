package com.domain.vehicle;

import com.google.auto.value.AutoValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AutoValue
public abstract class Vehicle {
    public abstract String vehicleRegistration();
    public abstract String make();
    public abstract String model();
    public abstract Integer year();
    public abstract Optional<String> description();
    public abstract VehicleInsurance insurance();

    public static Builder builder() {
        return new AutoValue_Vehicle.Builder();
    }

    public static Vehicle fromEntity(com.infrastructure.vehicle.Vehicle vehicle) {
        return Vehicle.create(vehicle.getVehicleRegistration(), vehicle.getMake(), vehicle.getModel(), vehicle.getYear(),
                              vehicle.getDescription(), VehicleInsurance.fromEntity(vehicle.getInsurance()));
    }

    public String[] retrieveValues(){
        List<String> vehicleValues = Arrays.asList(vehicleRegistration(), make(), model(), String.valueOf(year()),
                                                   description().orElse("EMPTY"));

        List<String> result = new ArrayList<>(vehicleValues);

        result.addAll(insurance().retrieveValues());

        return result.toArray(new String[0]);
    }

    public static Vehicle create(String vehicleRegistration,
                                 String make,
                                 String model,
                                 Integer year,
                                 Optional<String> description,
                                 VehicleInsurance insurance) {
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

        public abstract Builder insurance(VehicleInsurance insurance);

        public abstract Builder description(Optional<String> description);

        public abstract Builder make(String make);

        public abstract Vehicle build();
    }
}
