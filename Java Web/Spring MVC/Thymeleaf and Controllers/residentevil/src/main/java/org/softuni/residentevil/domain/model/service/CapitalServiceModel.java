package org.softuni.residentevil.domain.model.service;

import java.util.List;


public class CapitalServiceModel {

    private String id;
    private String name;
    private Double latitude;
    private Double longitude;
    private List<VirusServiceModel> viruses;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<VirusServiceModel> getViruses() {
        return this.viruses;
    }

    public void setViruses(List<VirusServiceModel> viruses) {
        this.viruses = viruses;
    }
}
