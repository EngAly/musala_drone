package com.musala.drones.service.impl;

import com.musala.drones.dto.LoadDroneDto;
import com.musala.drones.entity.Drone;
import com.musala.drones.entity.LoadMedication;
import com.musala.drones.enums.DroneLoadStatus;
import com.musala.drones.repository.LoadMedicationRepository;
import com.musala.drones.service.LoadMedicationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LoadMedicationServiceImpl implements LoadMedicationService {

    private LoadMedicationRepository loadMedicationRepo;

    public LoadMedicationServiceImpl(LoadMedicationRepository loadMedicationRepo) {
        this.loadMedicationRepo = loadMedicationRepo;
    }

    @Override
    public boolean save(List medications, Drone drone, LoadDroneDto loadDroneDto) {
        LoadMedication loadMedication = new LoadMedication();
        loadMedication.setMedications(medications);
        loadMedication.setDrone(drone);
        loadMedication.setSource(loadDroneDto.getSource());
        loadMedication.setDestination(loadDroneDto.getDestination());
        loadMedication.setDroneLoadStatus(DroneLoadStatus.LOADED);
        loadMedicationRepo.save(loadMedication);
        return true;
    }

    @Override
    public boolean isMedicationsLoaded(Set<String> medicationCodes) {
        return loadMedicationRepo.getLoadedMedications(medicationCodes) > 0;
    }

    @Override
    public Optional<LoadMedication> getDroneMedication(String serialNo) {
        return loadMedicationRepo.getByDrone_SerialNumber(serialNo);
    }

    @Override
    public boolean update(LoadMedication loadMedication) {
        loadMedicationRepo.save(loadMedication);
        return true;
    }
}
