package org.softuni.productshop.domain.model.binding;

import org.softuni.productshop.domain.entity.Category;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Set;

public class ProductBindingModel {

    @NotEmpty(message = "Name cannot be empty!")
    private String name;

    @NotEmpty(message = "Description cannot be empty!")
    private String description;

    @DecimalMin(value = "0.01", message = "Price cannot be empty!")
    private BigDecimal price;

    private MultipartFile imageUrl;

    @NotEmpty(message = "At least one category should be selected!")
    private Set<String> categories;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public MultipartFile getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(MultipartFile imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<String> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }
}
