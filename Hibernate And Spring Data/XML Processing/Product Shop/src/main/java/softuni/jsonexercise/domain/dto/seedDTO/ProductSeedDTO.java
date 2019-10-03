package softuni.jsonexercise.domain.dto.seedDTO;


import softuni.jsonexercise.domain.entities.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSeedDTO implements Serializable {

    @XmlElement
    @NotNull
    @Size(min = 3, message = "Product name should be at least 3 symbols long!")
    private String name;

    @XmlElement
    @NotNull
    private BigDecimal price;


    private User buyer;

    private User seller;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public User getBuyer() {
        return this.buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return this.seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
