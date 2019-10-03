package softuni.jsonexercise.domain.dto.queryDTO.query2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Set;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoldProductsModelViewWrapper implements Serializable {

    @XmlElement(name = "product")
    private Set<SoldProductsModelView> soldProducts;

    public SoldProductsModelViewWrapper(Set<SoldProductsModelView> soldProducts) {
        this.soldProducts = soldProducts;
    }

    public SoldProductsModelViewWrapper() {
    }

    public Set<SoldProductsModelView> getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(Set<SoldProductsModelView> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
