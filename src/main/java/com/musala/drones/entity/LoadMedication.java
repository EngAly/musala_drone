package com.musala.drones.entity;

import com.musala.drones.enums.DroneLoadStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "load_medication")
public class LoadMedication extends BaseEntity {

    private String source;
    private String destination;
    @OneToMany
    @JoinTable(name = "loaded_medications")
    private List<Medication> medications;
    @OneToOne
    private Drone drone;

    @Column(name = "DRONE_LOAD_STATUS")
    private DroneLoadStatus droneLoadStatus;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public DroneLoadStatus getDroneLoadStatus() {
        return droneLoadStatus;
    }

    public void setDroneLoadStatus(DroneLoadStatus droneLoadStatus) {
        this.droneLoadStatus = droneLoadStatus;
    }
}
