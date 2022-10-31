package com.musala.drones.service;

import com.musala.drones.dto.LoadDroneDto;
import com.musala.drones.entity.Drone;
import com.musala.drones.entity.LoadMedication;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface LoadMedicationService {

    boolean save(List medications, Drone drone, LoadDroneDto loadDroneDto);

    boolean isMedicationsLoaded(Set<String> medicationCodes);

    Optional<LoadMedication> getDroneMedication(String serialNo);
}
