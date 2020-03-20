package jsonprocessing.goldeneagle.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Table
@Entity(name = "products")
public class Product extends BaseEntity {

    @Column(nullable = false)
    @Size(min = 3, message = "Minimum 3 symbols required for product name!")
    private String name;
    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne()
    @JoinColumn(name = "buyer_id")
    private User buyer;

    @ManyToOne()
    @JoinColumn(name = "seller_id")
    private User seller;


    @ManyToMany()
    @JoinTable(name = "products_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

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

    public User getBuyer() {
        return this.buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return this.seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
