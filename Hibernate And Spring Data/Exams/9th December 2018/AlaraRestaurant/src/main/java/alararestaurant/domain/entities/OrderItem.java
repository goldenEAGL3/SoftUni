package alararestaurant.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "order_items")
public class OrderItem extends BaseEntity {

    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne()
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "quantity", nullable = false)
    @Min(value = 1)
    private Integer quantity;

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
