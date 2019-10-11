package mostwanted.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "districts")
public class District  extends  BaseEntity{
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "town_id")
    private Town town;

    @OneToMany(mappedBy = "district")
    private List<Race> race;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Town getTown() {
        return this.town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public List<Race> getRace() {
        return this.race;
    }

    public void setRace(List<Race> race) {
        this.race = race;
    }
}
