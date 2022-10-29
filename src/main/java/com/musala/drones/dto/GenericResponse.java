package com.musala.drones.dto;

import com.musala.drones.enums.ResponseStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GenericResponse implements Serializable {

    private ResponseStatus status = ResponseStatus.Success;
    private Object result;
    private String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    private String errorDetails;

    public GenericResponse(Object result) {
        this.result = result;
    }

    public GenericResponse(ResponseStatus status, String errorDetails) {
        this.status = status;
        this.errorDetails = errorDetails;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }
}