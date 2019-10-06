package alararestaurant.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {

    @Column(name = "name", nullable = false)
    @Size(min = 3, max = 30)
    private String name;

    @Column(name = "age", nullable = false)
    @Min(15)
    @Max(80)
    private Integer age;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id")
    private Position position;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Order> orders;

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

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
