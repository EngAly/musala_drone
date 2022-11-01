package com.musala.drones.dto;

import com.musala.drones.enums.ResponseStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GenericResponse implements Serializable {

    private ResponseStatus status = ResponseStatus.Success;
    private Object result;
    private String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    private List<String> errorMessage;
    private String errorDetails;

    public GenericResponse(Object result) {
        this.result = result;
    }

    public GenericResponse(ResponseStatus status, List<String> errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public GenericResponse(ResponseStatus status, List<String> errorMessage, String errorDetails) {
        this.status = status;
        this.errorMessage = errorMessage;
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

    public List<String> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(List<String> errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }
}
