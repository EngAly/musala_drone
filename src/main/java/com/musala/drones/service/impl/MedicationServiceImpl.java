package com.musala.drones.service.impl;

import com.musala.drones.DroneUtils;
import com.musala.drones.entity.Drone;
import com.musala.drones.entity.Medication;
import com.musala.drones.repository.MedicationRepository;
import com.musala.drones.service.MedicationService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

@Service
public class MedicationServiceImpl implements MedicationService {

    private MedicationRepository medicationRepo;

    public MedicationServiceImpl(MedicationRepository medicationRepo) {
        this.medicationRepo = medicationRepo;
    }

    public List<Medication> getMedicationsByCodes(Set<String> medicationSet) {

        if (DroneUtils.isEmpty(medicationSet)) return null;

        List<Medication> medicationList = medicationRepo.getByCodeIn(medicationSet);

        return (DroneUtils.isEmpty(medicationList) || medicationList.size() != medicationSet.size()) ?
                null : medicationList;

    }
}
