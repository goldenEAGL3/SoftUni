package org.softuni.residentevil.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "capitals")
public class Capital extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Double latitude;
    @Column(nullable = false)
    private Double longitude;
    @ManyToMany(mappedBy = "capitals")
    private List<Virus> viruses;
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

    public List<Virus> getViruses() {
        return this.viruses;
    }

    public void setViruses(List<Virus> viruses) {
        this.viruses = viruses;
    }
}
