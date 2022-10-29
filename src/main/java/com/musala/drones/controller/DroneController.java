package com.musala.drones.controller;

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
}
