package goldeneagle.carsdealer.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sale  extends BaseEntity{

    @OneToOne()
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Column(name = "discount_percentage", nullable = false)
    private double discountPercentage;

    public Car getCar() {
        return this.car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getDiscountPercentage() {
        return this.discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
