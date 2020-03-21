package goldeneagle.carsdealer.domain.dto.queryDTOs.query1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesModelView {

    @XmlElement
    private CarModelView car;
    @XmlElement(name = "discount")
    private double discountPercentage;

    public CarModelView getCar() {
        return this.car;
    }

    public void setCar(CarModelView car) {
        this.car = car;
    }

    public double getDiscountPercentage() {
        return this.discountPercentage * 100;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
