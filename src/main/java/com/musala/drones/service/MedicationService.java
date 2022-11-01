package com.musala.drones.service;

import com.musala.drones.entity.Medication;

import java.util.List;
import java.util.Set;

public interface MedicationService {

    List<Medication> getMedicationsByCodes(Set<String> medicationSet);

    void saveMedicationListDummyData();
}
