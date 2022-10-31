package com.musala.drones.repository;

import com.musala.drones.entity.Drone;
import com.musala.drones.enums.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {

    Optional<Drone> getDroneBySerialNumber(String serialNo);

    List<Drone> getDroneByState(DroneState state);
}
