package com.musala.drones.entity;

import com.musala.drones.enums.DroneModel;
import com.musala.drones.enums.DroneState;
import org.springframework.ui.Model;

import javax.persistence.*;

@Entity
@Table(name = "drone")
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serial_number", length = 100, unique = true)
    private String serialNumber;

    @Enumerated(EnumType.ORDINAL)
    private DroneModel model;

    @Column(name = "weight_limit")
    private Double weightLimit;

    @Column(name = "battery_capacity")
    private Integer batteryCapacity;

    @Enumerated(EnumType.ORDINAL)
    private DroneState state;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public DroneModel getModel() {
        return model;
    }

    public void setModel(DroneModel model) {
        this.model = model;
    }

    public Double getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(Double weightLimit) {
        this.weightLimit = weightLimit;
    }

    public Integer getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(Integer batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public DroneState getState() {
        return state;
    }

    public void setState(DroneState state) {
        this.state = state;
    }
}