package goldeneagle.carsdealer.domain.dto.queryDTOs.query3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocalSupplierModelViewWrapper {

    @XmlElement(name = "supplier")
    private List<LocalSupplierModelView> suppliers;

    public List<LocalSupplierModelView> getSuppliers() {
        return this.suppliers;
    }

    public void setSuppliers(List<LocalSupplierModelView> suppliers) {
        this.suppliers = suppliers;
    }
}
