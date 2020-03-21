package goldeneagle.carsdealer.domain.dto.seedDTOs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerSeedDTOWrapper {

    @XmlElement(name = "customer")
    private List<CustomerSeedDTO> customers;

    public List<CustomerSeedDTO> getCustomers() {
        return this.customers;
    }

    public void setCustomers(List<CustomerSeedDTO> customers) {
        this.customers = customers;
    }
}
