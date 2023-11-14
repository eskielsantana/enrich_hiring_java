package com.infrastructure;

import com.domain.vehicle.VehicleManager;
import com.infrastructure.vehicle.Vehicle;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class VehicleManagerImpl implements VehicleManager {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Vehicle> retrieveAllVehicles(){
        return entityManager.createNamedQuery("Vehicle.retrieveAllVehicles", Vehicle.class)
                            .getResultList();
    }
}
