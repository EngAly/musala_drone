package com.musala.drones.configuration;

import com.musala.drones.dto.GenericResponse;
import com.musala.drones.enums.ResponseStatus;
import com.musala.drones.exception.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ControllerException extends ResponseEntityExceptionHandler {


    @ExceptionHandler(DataNotFoundException.class)
    protected ResponseEntity handleDataNotFoundException(DataNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponse(ResponseStatus.Fail, ex.getMessage()));
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    protected ResponseEntity handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new GenericResponse(ResponseStatus.Fail, "Data already exists", ex.getMessage()));
    }


}
