package com.musala.drones.service;

import com.musala.drones.dto.DroneDto;
import com.musala.drones.dto.LoadDroneDto;
import com.musala.drones.entity.Drone;

public interface DroneService {

    DroneDto save(Drone drone);

    String getBatteryLevel(String serialNo);

    boolean loadDrone(LoadDroneDto loadDroneDto);
}
