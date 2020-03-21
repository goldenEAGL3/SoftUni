package xmlprocessing.goldeneagle.domain.dto.queryDTO.query2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoldProductsModelView {

    @XmlElement(name = "product")
    private List<ProductsSoldModelView> products;

    public List<ProductsSoldModelView> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductsSoldModelView> products) {
        this.products = products;
    }
}
