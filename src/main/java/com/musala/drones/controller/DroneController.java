package com.musala.drones.controller;

import com.musala.drones.dto.LoadDroneDto;
import com.musala.drones.entity.Drone;
import com.musala.drones.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drone")
public class DroneController extends GenericController {

    @Autowired
    private DroneService droneService;

    @PostMapping("/register")
    public ResponseEntity<?> registerDrone(@RequestBody Drone drone) {
        return getResponse(droneService.save(drone));
    }

    @GetMapping("/{serialNo}/battery-level")
    public ResponseEntity<?> registerDrone(@PathVariable String serialNo) {
        return getResponse(droneService.getBatteryLevel(serialNo));
    }

    @PostMapping("/load")
    public ResponseEntity<?> loadDrone(@RequestBody LoadDroneDto loadDroneDto) {
        return getResponse(droneService.loadDrone(loadDroneDto));
    }

    @PostMapping("/{serialNo}/deliver")
    public ResponseEntity<?> deliverLoadedDrone(@PathVariable String serialNo) {
        return getResponse(droneService.deliverLoadedDrone(serialNo));
    }

    @GetMapping("/{serialNo}/medications")
    public ResponseEntity<?> getDroneMedication(@PathVariable String serialNo) {
        return getResponse(droneService.getDroneMedication(serialNo));
    }

    @GetMapping("/available")
    public ResponseEntity<?> getAvailableDrones() {
        return getResponse(droneService.getAvailableDrones());
    }


}
