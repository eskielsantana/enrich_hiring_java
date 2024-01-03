package domain.vehicle;

import infrastructure.vehicle.Vehicle;

import java.util.List;

public interface VehicleRepository {
    List<Vehicle> retrieveAllVehicles();
}
