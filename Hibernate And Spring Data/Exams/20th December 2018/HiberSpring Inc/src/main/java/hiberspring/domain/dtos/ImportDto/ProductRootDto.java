package hiberspring.domain.dtos.ImportDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductRootDto {
    @XmlElement
    private List<ProductDto> product;

    public List<ProductDto> getProduct() {
        return this.product;
    }

    public void setProduct(List<ProductDto> product) {
        this.product = product;
    }
}
