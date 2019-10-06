package alararestaurant.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column(name = "name", nullable = false)
    @Size(min = 3, max = 30)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Item> items;

    public Category(@Size(min = 3, max = 30) String name) {
        this.name = name;
    }

    public Category() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
