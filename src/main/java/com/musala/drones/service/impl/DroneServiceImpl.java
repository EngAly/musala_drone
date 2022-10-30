package com.musala.drones.service.impl;

import com.musala.drones.dto.DroneDto;
import com.musala.drones.dto.LoadDroneDto;
import com.musala.drones.entity.Drone;
import com.musala.drones.enums.DroneState;
import com.musala.drones.exception.DataNotFoundException;
import com.musala.drones.repository.DroneRepository;
import com.musala.drones.service.DroneService;
import com.musala.drones.service.LoadMedicationService;
import com.musala.drones.service.MedicationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DroneServiceImpl implements DroneService {

    private final ModelMapper modelMapper;
    private final DroneRepository droneRepo;
    private final MedicationService medicationService;
    private final LoadMedicationService loadMedicationService;

    public DroneServiceImpl(DroneRepository droneRepo, ModelMapper modelMapper, MedicationService medicationService, LoadMedicationService loadMedicationService) {
        this.droneRepo = droneRepo;
        this.modelMapper = modelMapper;
        this.medicationService = medicationService;
        this.loadMedicationService = loadMedicationService;
    }

    @Override
    public DroneDto save(Drone drone) {
        drone.setId(null);
        drone.setState(DroneState.IDLE);
        return modelMapper.map(droneRepo.save(drone), DroneDto.class);
    }

    private DroneDto update(Drone drone) {
        return modelMapper.map(droneRepo.save(drone), DroneDto.class);
    }

    @Override
    public String getBatteryLevel(String serialNo) {

        Integer batteryCapacity = getDroneBySerialNumber(serialNo).getBatteryCapacity();

        return (batteryCapacity == null ? 0 : batteryCapacity) + "%";
    }

    private Drone getDroneBySerialNumber(String serialNo) {
        return droneRepo.getDroneBySerialNumber(serialNo).orElseThrow(() -> new DataNotFoundException("Drone not exists"));
    }

    @Override
    public boolean loadDrone(LoadDroneDto loadDroneDto) {

        // check drone is available
        Drone droneDB = getDroneBySerialNumber(loadDroneDto.getSerialNumber());
        if (!DroneState.IDLE.equals(droneDB.getState())) throw new DataNotFoundException("Drone already in progress");

        // check medications are exists
        List medications = medicationService.getMedicationsByCodes(loadDroneDto.getCodes());
        if (medications == null) throw new DataNotFoundException("Medications not exists");

        // check medications not loaded before
        boolean isMedLoaded = loadMedicationService.isMedicationsLoaded(loadDroneDto.getCodes());
        if (isMedLoaded) throw new DataNotFoundException("Medications already loaded");

        changeDroneState(droneDB, DroneState.LOADING);
        loadMedicationService.save(medications, droneDB, loadDroneDto);
        changeDroneState(droneDB, DroneState.LOADED);
        return true;
    }

    private void changeDroneState(Drone drone, DroneState droneState) {
        drone.setState(droneState);
        update(drone);
    }
}
