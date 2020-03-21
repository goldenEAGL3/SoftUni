package xmlprocessing.goldeneagle.domain.dto.queryDTO.query1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductInRangeModelViewWrapper {

    @XmlElement(name = "product")
    List<ProductInRangeModelView> products;

    public List<ProductInRangeModelView> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductInRangeModelView> products) {
        this.products = products;
    }
}
