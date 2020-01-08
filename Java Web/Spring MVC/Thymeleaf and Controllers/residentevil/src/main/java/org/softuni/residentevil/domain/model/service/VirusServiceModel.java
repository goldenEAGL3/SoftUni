package org.softuni.residentevil.domain.model.service;

import org.softuni.residentevil.domain.entity.enums.Creator;
import org.softuni.residentevil.domain.entity.enums.Magnitude;
import org.softuni.residentevil.domain.entity.enums.Mutation;

import java.time.LocalDate;
import java.util.List;

public class VirusServiceModel {

    private String id;

    private String name;

    private String description;

    private String sideEffects;

    private Creator creator;

    private boolean isDeadly;

    private boolean isCurable;

    private Mutation mutation;

    private Integer turnoverRate;

    private Integer hoursUntilTurn;

    private Magnitude magnitude;

    private LocalDate releasedOn;

    private List<CapitalServiceModel> capitals;

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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSideEffects() {
        return this.sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public Creator getCreator() {
        return this.creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public boolean isDeadly() {
        return this.isDeadly;
    }

    public void setDeadly(boolean deadly) {
        isDeadly = deadly;
    }

    public boolean isCurable() {
        return this.isCurable;
    }

    public void setCurable(boolean curable) {
        isCurable = curable;
    }

    public Mutation getMutation() {
        return this.mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    public Integer getTurnoverRate() {
        return this.turnoverRate;
    }

    public void setTurnoverRate(Integer turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public Integer getHoursUntilTurn() {
        return this.hoursUntilTurn;
    }

    public void setHoursUntilTurn(Integer hoursUntilTurn) {
        this.hoursUntilTurn = hoursUntilTurn;
    }

    public Magnitude getMagnitude() {
        return this.magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    public LocalDate getReleasedOn() {
        return this.releasedOn;
    }

    public void setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
    }

    public List<CapitalServiceModel> getCapitals() {
        return this.capitals;
    }

    public void setCapitals(List<CapitalServiceModel> capitals) {
        this.capitals = capitals;
    }
}
