package mostwanted.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "races")
public class Race extends BaseEntity{
    @Column(name = "laps", nullable = false)
    private Integer laps;

    @ManyToOne()
    @JoinColumn(name = "district_id")
    private District district;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL)
    private List<RaceEntry> raceEntries;

    public Integer getLaps() {
        return this.laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    public District getDistrict() {
        return this.district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<RaceEntry> getRaceEntries() {
        return this.raceEntries;
    }

    public void setRaceEntries(List<RaceEntry> raceEntries) {
        this.raceEntries = raceEntries;
    }
}
