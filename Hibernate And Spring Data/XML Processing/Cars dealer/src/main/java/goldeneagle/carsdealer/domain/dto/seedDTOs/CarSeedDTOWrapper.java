package goldeneagle.carsdealer.domain.dto.seedDTOs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarSeedDTOWrapper {

    @XmlElement(name = "car")
    private List<CarSeedDTO> cars;

    public List<CarSeedDTO> getCars() {
        return this.cars;
    }

    public void setCars(List<CarSeedDTO> cars) {
        this.cars = cars;
    }
}
