package domain.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    @Autowired
    public VehicleRepository vehicleRepository;

    public List<Vehicle> getRegisteredVehicles() {
        return vehicleRepository.retrieveAllVehicles().stream().map(Vehicle::fromEntity).collect(Collectors.toList());
    }
}
