package org.softuni.productshop.domain.model.view.order;

import org.softuni.productshop.domain.model.view.product.ProductViewModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDetailsViewModel {

    private String id;
    private ProductViewModel product;
    private String customerUsername;
    private LocalDateTime orderDate;
    private Integer quantity;
    private BigDecimal totalPrice;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductViewModel getProduct() {
        return this.product;
    }

    public void setProduct(ProductViewModel product) {
        this.product = product;
    }

    public String getCustomerUsername() {
        return this.customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public LocalDateTime getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
