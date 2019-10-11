package mostwanted.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "racers")
public class Racer extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "bounty")

    private BigDecimal bounty;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "town_id")
    private Town homeTown;

    @OneToMany(mappedBy = "racer")
    private List<Car> cars;

    @OneToMany(mappedBy = "racer")
    private List<RaceEntry> raceEntries;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getBounty() {
        return this.bounty;
    }

    public void setBounty(BigDecimal bounty) {
        this.bounty = bounty;
    }

    public Town getHomeTown() {
        return this.homeTown;
    }

    public void setHomeTown(Town homeTown) {
        this.homeTown = homeTown;
    }

    public List<Car> getCars() {
        return this.cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<RaceEntry> getRaceEntries() {
        return this.raceEntries;
    }

    public void setRaceEntries(List<RaceEntry> raceEntries) {
        this.raceEntries = raceEntries;
    }
}
