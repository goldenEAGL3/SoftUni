package hiberspring.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "branches")
public class Branch extends BaseEntity {

    @Column(name = "name", nullable = false)

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "town_id")
    private Town town;

    @OneToMany(mappedBy = "branch")
    private List<Employee> employees;

    @OneToMany(mappedBy = "branch")
    private List<Product> products;

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

    public List<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
