package org.softuni.residentevil.domain.model.binding;

import org.softuni.residentevil.domain.entity.enums.Creator;
import org.softuni.residentevil.domain.entity.enums.Magnitude;
import org.softuni.residentevil.domain.entity.enums.Mutation;
import org.softuni.residentevil.util.annotations.CreatorValidation;
import org.softuni.residentevil.util.annotations.DateValidation;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public class VirusBindingModel {

    private String id;

    @NotNull(message = "Invalid name!")
    @Size(min = 3, max = 10, message = "Invalid name!")
    private String name;

    @NotNull(message = "Invalid description!")
    @Size(min = 5, max = 100, message = "Description should be between 5 and 50 symbols!")
    private String description;

    @Size(max = 50, message = "Side effect should be maximum 50 symbols!")
    private String sideEffects;

    @CreatorValidation
    private Creator creator;

    private boolean isDeadly;

    private boolean isCurable;

    @NotNull(message = "Incorrect data for mutation")
    private Mutation mutation;

    @NotNull(message = "Value should be between 0 and 100.")
    private Integer turnoverRate;

    @NotNull(message = "Value should be between 1 and 12")
    private Integer hoursUntilTurn;

    @NotNull(message = "Incorrect data for magnitude")
    private Magnitude magnitude;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @DateValidation
    private LocalDate releasedOn;

    @NotEmpty(message = "At least one capital should be infected.")
    private List<String> capitals;

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

    public List<String> getCapitals() {
        return this.capitals;
    }

    public void setCapitals(List<String> capitals) {
        this.capitals = capitals;
    }
}
