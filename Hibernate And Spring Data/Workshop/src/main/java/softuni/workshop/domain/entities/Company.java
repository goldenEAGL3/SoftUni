package softuni.workshop.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company extends BaseEntity{

    @Column(name = "name", nullable = false)
    @Size(min = 3, max = 15, message = "Company name must be between 3 and 15 symbols long.")
    private String name;

    @OneToMany(mappedBy = "company")
    private List<Project> projects;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Project> getProjects() {
        return this.projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
