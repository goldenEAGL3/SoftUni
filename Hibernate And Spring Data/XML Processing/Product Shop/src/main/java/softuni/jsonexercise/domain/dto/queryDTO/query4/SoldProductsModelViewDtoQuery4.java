package softuni.jsonexercise.domain.dto.queryDTO.query4;



import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SoldProductsModelViewDtoQuery4 {
    @XmlAttribute
    private Integer count;
    @XmlElement(name = "product")
    private List<ProductModelViewDtoQuery4> products;

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ProductModelViewDtoQuery4> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductModelViewDtoQuery4> products) {
        this.products = products;
    }
}
