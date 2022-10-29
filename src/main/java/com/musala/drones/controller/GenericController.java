package com.musala.drones.controller;

import com.musala.drones.dto.GenericResponse;
import org.springframework.http.ResponseEntity;

public abstract class GenericController {

    protected ResponseEntity<?> getResponse(Object data) {
        return ResponseEntity.ok().body(new GenericResponse(data));
    }
}
