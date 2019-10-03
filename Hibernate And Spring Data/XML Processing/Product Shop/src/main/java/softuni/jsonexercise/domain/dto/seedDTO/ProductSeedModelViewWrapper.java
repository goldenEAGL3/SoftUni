package softuni.jsonexercise.domain.dto.seedDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSeedModelViewWrapper implements Serializable {

    @XmlElement(name = "product")
    private List<ProductSeedDTO> products;

    public List<ProductSeedDTO> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductSeedDTO> products) {
        this.products = products;
    }
}
