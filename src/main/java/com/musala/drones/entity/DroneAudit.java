package com.musala.drones.entity;

import com.musala.drones.enums.DroneModel;
import com.musala.drones.enums.DroneState;

import javax.persistence.*;

@Entity
@Table(name = "drone_audit")
public class DroneAudit extends BaseEntity {

    @Column(name = "drone_id")
    private Long droneId;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "battery_capacity")
    private Integer batteryCapacity;

    public DroneAudit() {
    }

    public DroneAudit(Long droneId, String serialNumber, Integer batteryCapacity) {
        this.droneId = droneId;
        this.serialNumber = serialNumber;
        this.batteryCapacity = batteryCapacity;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(Integer batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public Long getDroneId() {
        return droneId;
    }

    public void setDroneId(Long droneId) {
        this.droneId = droneId;
    }
}
