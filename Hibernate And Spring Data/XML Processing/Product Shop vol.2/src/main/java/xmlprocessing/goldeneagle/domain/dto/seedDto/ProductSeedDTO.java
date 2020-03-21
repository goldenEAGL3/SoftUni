package xmlprocessing.goldeneagle.domain.dto.seedDto;


import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSeedDTO {

    @XmlElement
    @NotNull
    @Size(min = 3, message = "Minimum 3 symbols required for product name!")
    private String name;

    @XmlElement
    @DecimalMin(value = "0.01", message = "Product price must be positive number!")
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
