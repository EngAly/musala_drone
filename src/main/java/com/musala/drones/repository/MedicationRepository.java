package com.musala.drones.repository;

import com.musala.drones.entity.Drone;
import com.musala.drones.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MedicationRepository extends JpaRepository<Medication, Long> {

    Optional<Medication> getMedicationByCode(String code);

    List<Medication> getByCodeIn(Set<String> code);
}
