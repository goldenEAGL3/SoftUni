package org.softuni.residentevil.domain.entity;

import org.softuni.residentevil.domain.entity.enums.Creator;
import org.softuni.residentevil.domain.entity.enums.Magnitude;
import org.softuni.residentevil.domain.entity.enums.Mutation;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "viruses")
public class Virus extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "side_effects")
    private String sideEffects;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Creator creator;

    @Column()
    private boolean isDeadly;

    @Column()
    private boolean isCurable;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Mutation mutation;

    @Column(name = "turnover_rate")
    private Integer turnoverRate;

    @Column(name = "hours_until_turn")
    private Integer hoursUntilTurn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Magnitude magnitude;

    @Column(name = "released_on")
    private LocalDate releasedOn;

    @ManyToMany()
    @JoinTable(name = "viruses_capitals",
            joinColumns = @JoinColumn(name = "virus_id"),
            inverseJoinColumns = @JoinColumn(name = "capital_id")
    )
    private List<Capital> capitals;

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

    public List<Capital> getCapitals() {
        return this.capitals;
    }

    public void setCapitals(List<Capital> capitals) {
        this.capitals = capitals;
    }
}
