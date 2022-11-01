package com.musala.drones.configuration;

import com.musala.drones.entity.Drone;
import com.musala.drones.entity.DroneAudit;
import com.musala.drones.repository.DroneAuditRepository;
import com.musala.drones.repository.DroneRepository;
import com.musala.drones.util.DroneConstants;
import com.musala.drones.util.DroneUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.MessageFormat;
import java.util.List;

@EnableScheduling
@Configuration
public class DroneSchedule {

    private Logger log = LoggerFactory.getLogger(DroneSchedule.class);
    private DroneRepository droneRepository;
    private DroneAuditRepository droneAuditRepository;

    public DroneSchedule(DroneRepository droneRepository, DroneAuditRepository droneAuditRepository) {
        this.droneRepository = droneRepository;
        this.droneAuditRepository = droneAuditRepository;
    }

    @Scheduled(fixedRate = DroneConstants.DRONE_SCHEDULE_RATE)
    public void checkDronesBatteryLevels() {

        List<Drone> drones = droneRepository.findAll();

        if (DroneUtils.isEmpty(drones)) return;

        for (Drone drone : drones) {
            droneAuditRepository.save(new DroneAudit(drone.getId(), drone.getSerialNumber(), drone.getBatteryCapacity()));
            log.info(MessageFormat.format("Drone with serial number {0} battery capacity {1}%", drone.getSerialNumber(), drone.getBatteryCapacity()));
        }
    }

}
