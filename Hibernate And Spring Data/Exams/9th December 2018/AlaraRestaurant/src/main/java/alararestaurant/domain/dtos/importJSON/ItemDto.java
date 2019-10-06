package alararestaurant.domain.dtos.importJSON;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ItemDto {

    @Expose
    @Size(min = 3, max = 30)
    private String name;

    @Expose
    @DecimalMin(value = "0.01")
    private BigDecimal price;

    @Expose
    @Size(min = 3, max = 30)
    private String category;

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

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
