package softuni.jsonexercise.domain.dto.queryDTO.query2;


import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Set;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersWithAtLeastOneProductSoldModelView implements Serializable {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlElement(name = "sold-products")
    private SoldProductsModelViewWrapper soldProducts;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public SoldProductsModelViewWrapper getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(SoldProductsModelViewWrapper soldProducts) {
        this.soldProducts = soldProducts;
    }
}
