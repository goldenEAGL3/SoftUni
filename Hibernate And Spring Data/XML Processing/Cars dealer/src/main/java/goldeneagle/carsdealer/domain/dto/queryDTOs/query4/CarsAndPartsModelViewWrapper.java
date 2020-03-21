package goldeneagle.carsdealer.domain.dto.queryDTOs.query4;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsAndPartsModelViewWrapper {

    @XmlElement(name = "car")
    private List<CarsAndPartsModelView> cars;

    public List<CarsAndPartsModelView> getCars() {
        return this.cars;
    }

    public void setCars(List<CarsAndPartsModelView> cars) {
        this.cars = cars;
    }
}
