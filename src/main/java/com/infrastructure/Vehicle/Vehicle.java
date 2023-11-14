package com.infrastructure.Vehicle;

import com.infrastructure.Insurance.Insurance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name="vehicle")
@NamedQueries({
        @NamedQuery(name="Vehicle.retrieveAllVehicles", query="SELECT DISTINCT v FROM Vehicle v LEFT JOIN FETCH v.insurance vi WHERE vi.isActive = true"),
})
public class Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "vehicle_registration", nullable = false)
    private String vehicleRegistration;
    @Column(nullable = false)
    private String make;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private Integer year;
    @Column
    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_registration", referencedColumnName = "vehicle_registration")
    private List<Insurance> insurance;

    public Vehicle() {
    }

    public String getVehicleRegistration() {
        return vehicleRegistration;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public Insurance getInsurance() {
        return insurance.get(0);
    }
}
