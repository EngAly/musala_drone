package com.musala.drones.service;

import com.musala.drones.dto.DroneDto;
import com.musala.drones.dto.LoadDroneDto;
import com.musala.drones.entity.Drone;
import com.musala.drones.entity.Medication;

import java.util.List;

public interface DroneService {

    DroneDto save(Drone drone);

    String getBatteryLevel(String serialNo);

    boolean loadDrone(LoadDroneDto loadDroneDto);
    boolean deliverLoadedDrone(String serialNo);

    List<Medication> getDroneMedication(String serialNo);

    List<DroneDto> getAvailableDrones();
}
