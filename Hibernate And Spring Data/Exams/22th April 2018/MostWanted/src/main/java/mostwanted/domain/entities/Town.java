package mostwanted.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity{
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "town")
    private List<District> districts;

    @OneToMany(mappedBy = "homeTown")
    private List<Racer> racers;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<District> getDistricts() {
        return this.districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public List<Racer> getRacers() {
        return this.racers;
    }

    public void setRacers(List<Racer> racers) {
        this.racers = racers;
    }
}
