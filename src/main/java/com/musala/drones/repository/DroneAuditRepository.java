package com.musala.drones.repository;

import com.musala.drones.entity.DroneAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneAuditRepository extends JpaRepository<DroneAudit, Long> {
}
