package hiberspring.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity {

    @Column(name = "name", nullable = false)

    private String name;

    @Column(name = "population")
    private Integer population;

    @OneToMany(mappedBy = "town")
    private List<Branch> branches;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return this.population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public List<Branch> getBranches() {
        return this.branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}
