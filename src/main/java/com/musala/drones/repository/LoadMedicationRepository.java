package com.musala.drones.repository;

import com.musala.drones.entity.LoadMedication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface LoadMedicationRepository extends JpaRepository<LoadMedication, Long> {

    @Query(value = "SELECT COUNT(lmm.load_medication_id)  FROM loaded_medications lmm join medication m on lmm.medications_id=m.id " +
            "where m.code in :codes", nativeQuery = true)
    int getLoadedMedications(Set<String> codes);

    Optional<LoadMedication> getByDrone_SerialNumber(String serialNo);
}
