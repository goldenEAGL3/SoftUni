package goldeneagle.carsdealer.domain.dto.seedDTOs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartsSeedDTOWrapper {

    @XmlElement(name = "part")
    private List<PartsSeedDTO> parts;

    public List<PartsSeedDTO> getParts() {
        return this.parts;
    }

    public void setParts(List<PartsSeedDTO> parts) {
        this.parts = parts;
    }
}
