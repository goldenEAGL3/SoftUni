package goldeneagle.carsdealer.domain.dto.seedDTOs;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PartsSeedDTO {

    @Expose
    @NotNull()
    private String name;
    @Expose
    @DecimalMin(value = "0.01", message = "Price must be positive number!")
    private BigDecimal price;
    @Expose
    @Min(value = 0, message = "Quantity must be positive number!")
    private int quantity;

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
}
