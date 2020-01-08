package org.softuni.productshop.domain.model.binding;

import javax.validation.constraints.NotEmpty;

public class CategoryBindingModel {

    @NotEmpty
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
