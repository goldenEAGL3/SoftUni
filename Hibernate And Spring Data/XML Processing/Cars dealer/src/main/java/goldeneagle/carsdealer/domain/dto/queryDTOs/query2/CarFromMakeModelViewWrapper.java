package goldeneagle.carsdealer.domain.dto.queryDTOs.query2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarFromMakeModelViewWrapper {

    @XmlElement(name = "car")
    private List<CarFromMakeModelView> cars;

    public List<CarFromMakeModelView> getCars() {
        return this.cars;
    }

    public void setCars(List<CarFromMakeModelView> cars) {
        this.cars = cars;
    }
}
