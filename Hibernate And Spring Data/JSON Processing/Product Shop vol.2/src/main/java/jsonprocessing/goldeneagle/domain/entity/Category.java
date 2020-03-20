package jsonprocessing.goldeneagle.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column
    @Size(min = 3, max = 15)
    private String name;

    public String getName() {
        return this.name;
    }

    @ManyToMany(mappedBy = "categories")
    Set<Product> products;

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
