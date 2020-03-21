package xmlprocessing.goldeneagle.domain.dto.seedDto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSeedDTOWrapper {

    @XmlElement(name = "product")
    List<ProductSeedDTO> products;

    public List<ProductSeedDTO> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductSeedDTO> products) {
        this.products = products;
    }
}
