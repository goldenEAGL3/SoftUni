package jsonprocessing.goldeneagle.domain.dto.seedDto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ProductSeedDTO {

    @Expose
    @NotNull
    @Size(min = 3, message = "Minimum 3 symbols required for product name!")
    private String name;

    @Expose
    @NotNull
    private BigDecimal price;

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
}
