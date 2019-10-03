package softuni.jsonexercise.domain.dto.queryDTO.query4;



import javax.xml.bind.annotation.*;
import java.io.Serializable;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserViewModelDtoQuery4 implements Serializable {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlAttribute(name = "age")
    private Integer age;

    @XmlElement(name = "sold-products")
    private SoldProductsModelViewDtoQuery4 soldProducts;

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

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public SoldProductsModelViewDtoQuery4 getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(SoldProductsModelViewDtoQuery4 soldProducts) {
        this.soldProducts = soldProducts;
    }
}
