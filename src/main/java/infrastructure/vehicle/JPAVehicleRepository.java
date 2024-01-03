package infrastructure.vehicle;

import domain.vehicle.VehicleRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JPAVehicleRepository implements VehicleRepository {

    @PersistenceContext private EntityManager em;

    @Override
    public List<Vehicle> retrieveAllVehicles() {
        return em.createNamedQuery("Vehicle.retrieveAllVehicles", Vehicle.class)
                            .getResultList();
    }
}