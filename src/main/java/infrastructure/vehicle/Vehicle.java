package infrastructure.vehicle;

import infrastructure.insurance.Insurance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "vehicle")
@NamedQuery(name = "Vehicle.retrieveAllVehicles", query = "SELECT DISTINCT v FROM Vehicle v")
public class Vehicle implements Serializable {
    @Serial
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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicle_registration", referencedColumnName = "vehicle_registration")
    private List<Insurance> insurance;

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
