package com.infrastructure.insurance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "vehicle_insurance")
public class Insurance implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "policy_number", nullable = false)
    private String policyNumber;

    @Column(name = "vehicle_registration", nullable = false)
    private String vehicleRegistration;

    @Column(name = "start_date", nullable = false)
    private Long startDate;

    @Column(name = "end_date", nullable = false)
    private Long endDate;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    public Insurance() {}

    public String getPolicyNumber() {
        return policyNumber;
    }

    public Instant getStartDate() {
        return Instant.ofEpochMilli(startDate);
    }

    public Instant getEndDate() {
        return Instant.ofEpochMilli(endDate);
    }
}
