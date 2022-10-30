package com.musala.drones.dto;

import java.util.Set;

public class LoadDroneDto {

    private String source;
    private String destination;

    private String serialNumber;
    private Set codes;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Set getCodes() {
        return codes;
    }

    public void setCodes(Set codes) {
        this.codes = codes;
    }
}
