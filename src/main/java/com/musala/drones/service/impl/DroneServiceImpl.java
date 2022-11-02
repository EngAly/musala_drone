package com.musala.drones.service.impl;

import com.musala.drones.dto.DroneDto;
import com.musala.drones.dto.LoadDroneDto;
import com.musala.drones.entity.Drone;
import com.musala.drones.entity.LoadMedication;
import com.musala.drones.entity.Medication;
import com.musala.drones.enums.DroneLoadStatus;
import com.musala.drones.enums.DroneState;
import com.musala.drones.exception.DataNotFoundException;
import com.musala.drones.repository.DroneRepository;
import com.musala.drones.service.DroneService;
import com.musala.drones.service.LoadMedicationService;
import com.musala.drones.service.MedicationService;
import com.musala.drones.util.DroneConstants;
import com.musala.drones.util.DroneUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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

        if (getDroneBySerialNumber(drone.getSerialNumber()).isPresent()) {
            throw new DataNotFoundException("Drone Already Exists");
        }

        drone.setId(null);
        drone.setState(DroneState.IDLE);
        return modelMapper.map(droneRepo.save(drone), DroneDto.class);
    }

    private DroneDto update(Drone drone) {
        return modelMapper.map(droneRepo.save(drone), DroneDto.class);
    }

    @Override
    public String getBatteryLevel(String serialNo) {

        Integer batteryCapacity = getDroneBySerialNumber(serialNo)
                .orElseThrow(() -> new DataNotFoundException("Drone not exists"))
                .getBatteryCapacity();

        return (batteryCapacity == null ? 0 : batteryCapacity) + "%";
    }

    private Optional<Drone> getDroneBySerialNumber(String serialNo) {
        return droneRepo.getDroneBySerialNumber(serialNo);
    }

    @Override
    public boolean loadDrone(LoadDroneDto loadDroneDto) {

        if (DroneUtils.isEmpty(loadDroneDto.getCodes())) {
            throw new DataNotFoundException("Medications codes not exists");
        }
        // check drone is available
        Drone droneDB = validateDrone(loadDroneDto);

        // check medications are exists
        List<Medication> medications = medicationService.getMedicationsByCodes(loadDroneDto.getCodes());
        if (medications == null) throw new DataNotFoundException("Medications not exists");

        double medicationsWeight = medications.stream().mapToDouble(Medication::getWeight).sum();

        if (medicationsWeight > droneDB.getWeightLimit())
            throw new DataNotFoundException(String.format("Medications weight (%s) more than drone weight (%s)", medicationsWeight, droneDB.getWeightLimit()));

        // check medications not loaded before
        boolean isMedLoaded = loadMedicationService.isMedicationsLoaded(loadDroneDto.getCodes());
        if (isMedLoaded) throw new DataNotFoundException("Medications already loaded");

        changeDroneState(droneDB, DroneState.LOADING);
        loadMedicationService.save(medications, droneDB, loadDroneDto);
        changeDroneState(droneDB, DroneState.LOADED);
        return true;
    }

    @Override
    public List<Medication> getDroneMedication(String serialNo) {
        return loadMedicationService.getDroneMedication(serialNo)
                .map(LoadMedication::getMedications)
                .orElse(null);
    }

    @Override
    public boolean deliverLoadedDrone(String serialNo) {

        LoadMedication droneLoad = loadMedicationService.getDroneMedication(serialNo)
                .orElseThrow(() -> new DataNotFoundException("Drone not Loaded Yet"));

        if (!DroneLoadStatus.LOADED.equals(droneLoad.getDroneLoadStatus()))
            throw new DataNotFoundException("Drone must be in  Loaded Status to be delivered");

        // set drone to delivering state
        Drone droneDB = droneLoad.getDrone();
        droneDB.setState(DroneState.DELIVERING);
        update(droneDB);

        droneLoad.setDroneLoadStatus(DroneLoadStatus.DELIVERED);
        loadMedicationService.update(droneLoad);

        // set drone to delivering state
        droneDB.setState(DroneState.DELIVERED);
        update(droneDB);

        return true;
    }

    @Override
    public List<DroneDto> getAvailableDrones() {
        return droneRepo.getDroneByState(DroneState.IDLE)
                .stream().map(drone -> modelMapper.map(drone, DroneDto.class))
                .collect(Collectors.toList());
    }

    private void changeDroneState(Drone drone, DroneState droneState) {
        drone.setState(droneState);
        update(drone);
    }

    private Drone validateDrone(LoadDroneDto loadDroneDto) {
        Drone droneDB = getDroneBySerialNumber(loadDroneDto.getSerialNumber()).orElseThrow(() -> new DataNotFoundException("Drone not exists"));

        if (!DroneState.IDLE.equals(droneDB.getState())) throw new DataNotFoundException("Drone already in progress");

        if (droneDB.getBatteryCapacity() < DroneConstants.DRONE_BATTERY_MIN_CAPACITY)
            throw new DataNotFoundException("Drone capacity less than " + DroneConstants.DRONE_BATTERY_MIN_CAPACITY + "%");

        return droneDB;
    }

}
