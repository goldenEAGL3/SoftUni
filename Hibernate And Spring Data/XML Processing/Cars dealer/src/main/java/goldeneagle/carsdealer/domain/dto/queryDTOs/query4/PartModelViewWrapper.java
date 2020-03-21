package goldeneagle.carsdealer.domain.dto.queryDTOs.query4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartModelViewWrapper {

    @XmlElement(name = "part")
    private List<PartModelView> parts;

    public List<PartModelView> getParts() {
        return this.parts;
    }

    public void setParts(List<PartModelView> parts) {
        this.parts = parts;
    }
}
