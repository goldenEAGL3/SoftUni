package alararestaurant.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "positions")
public class Position extends BaseEntity {

    @Column(name = "name")
    @Size(min = 3, max = 30)
    private String name;

    @OneToMany(mappedBy = "position")
    private Set<Employee> employees;

    public Position(String name) {
        this.name = name;
    }

    public Position() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
