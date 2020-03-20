package goldeneagle.carsdealer.domain.dto.queryDTOs.query4;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class PartModelView {

    @Expose
    @SerializedName("Name")
    private String name;
    @Expose
    @SerializedName("Price")
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
