package goldeneagle.carsdealer.domain.dto.seedDTOs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierSeedDTOWrapper {

    @XmlElement(name = "supplier")
    private List<SupplierSeedDTO> suppliers;

    public List<SupplierSeedDTO> getSuppliers() {
        return this.suppliers;
    }

    public void setSuppliers(List<SupplierSeedDTO> suppliers) {
        this.suppliers = suppliers;
    }
}
