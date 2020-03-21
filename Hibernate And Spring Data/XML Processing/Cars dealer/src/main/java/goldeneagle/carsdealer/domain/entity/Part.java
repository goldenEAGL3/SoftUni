package goldeneagle.carsdealer.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "parts")
public class Part extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @DecimalMin(value = "0.01", message = "Price must be positive number!")
    private BigDecimal price;
    @Column(nullable = false)
    @Min(value = 3, message = "Quantity must be positive number!")
    private int quantity;

    @ManyToMany(mappedBy = "parts")
    private List<Car> cars;

    @ManyToOne()
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Car> getCars() {
        return this.cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Supplier getSupplier() {
        return this.supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
