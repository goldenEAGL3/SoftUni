package xmlprocessing.goldeneagle.domain.dto.queryDTO.query4;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoldProductsModelView {

    @XmlAttribute
    private int count;
    @XmlElement(name = "product")
    private List<ProductsModelView> products;

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProductsModelView> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductsModelView> products) {
        this.products = products;
    }
}
