package org.softuni.productshop.domain.model.service;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderServiceModel {
    private String id;
    private ProductServiceModel product;
    private UserServiceModel customer;
    private LocalDateTime orderDate;
    private Integer quantity;
    private BigDecimal totalPrice;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductServiceModel getProduct() {
        return this.product;
    }

    public void setProduct(ProductServiceModel product) {
        this.product = product;
    }

    public UserServiceModel getCustomer() {
        return this.customer;
    }

    public void setCustomer(UserServiceModel customer) {
        this.customer = customer;
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
