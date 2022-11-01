package com.musala.drones.entity;


import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "medication")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Medication extends BaseEntity {

    @Pattern(regexp = "^[-a-zA-Z_\\d]*$", message = "Medicine name not valid")
    private String name;
    private Double weight;

    @Column(unique = true)
    private String code;

    @Column(columnDefinition = "BLOB")
    private byte[] image;

    public Medication() {
    }

    public Medication(String name, Double weight, String code) {
        this.name = name;
        this.weight = weight;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
