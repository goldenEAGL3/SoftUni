package org.softuni.productshop.domain.model.binding;

import javax.validation.constraints.Min;

public class OrderProductBindingModel {

    @Min(value = 1, message = "Quantity should be at least 1.")
    private Integer quantity;

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
