package goldeneagle.carsdealer.domain.dto.queryDTOs.query1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderedCustomersModelViewWrapper {

    @XmlElement(name = "customer")
    private List<OrderedCustomerModelView> customers;

    public List<OrderedCustomerModelView> getCustomers() {
        return this.customers;
    }

    public void setCustomers(List<OrderedCustomerModelView> customers) {
        this.customers = customers;
    }
}
