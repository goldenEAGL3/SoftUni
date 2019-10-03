package softuni.jsonexercise.domain.dto.queryDTO.query1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductInRangeModelViewWrapper {

    @XmlElement
    private List<ProductInRangeModelView> products;

    public ProductInRangeModelViewWrapper(List<ProductInRangeModelView> products) {
        this.products = products;
    }

    public ProductInRangeModelViewWrapper() {
    }

    public List<ProductInRangeModelView> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductInRangeModelView> products) {
        this.products = products;
    }
}
