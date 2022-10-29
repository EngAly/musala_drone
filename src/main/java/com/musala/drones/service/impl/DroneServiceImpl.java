package com.musala.drones.service.impl;

import com.musala.drones.dto.DroneDto;
import com.musala.drones.entity.Drone;
import com.musala.drones.exception.DataNotFoundException;
import com.musala.drones.repository.DroneRepository;
import com.musala.drones.service.DroneService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepo;
    private final ModelMapper modelMapper;

    public DroneServiceImpl(DroneRepository droneRepo, ModelMapper modelMapper) {
        this.droneRepo = droneRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public DroneDto save(Drone drone) {
        drone.setId(null);
        return modelMapper.map(droneRepo.save(drone), DroneDto.class);
    }

    @Override
    public String getBatteryLevel(String serialNo) {

        Integer batteryCapacity = droneRepo.getDroneBySerialNumber(serialNo)
                .orElseThrow(() -> new DataNotFoundException("Drone not exists"))
                .getBatteryCapacity();

        return (batteryCapacity == null ? 0 : batteryCapacity) + "%";
    }

}
