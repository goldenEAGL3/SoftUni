package xmlprocessing.goldeneagle.domain.dto.queryDTO.query4;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersWithAtLeastOneProductSoldModelView {

    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last-name")
    private String lastName;
    @XmlAttribute
    private int age;
    @XmlElement(name = "sold-products")
    private SoldProductsModelView soldProducts;

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

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public SoldProductsModelView getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(SoldProductsModelView soldProducts) {
        this.soldProducts = soldProducts;
    }
}
