package goldeneagle.carsdealer.domain.dto.queryDTOs.query5;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersTotalSalesModelViewWrapper {

    @XmlElement(name = "customer")
    private List<CustomersTotalSalesModelView> customers;

    public List<CustomersTotalSalesModelView> getCustomers() {
        return this.customers;
    }

    public void setCustomers(List<CustomersTotalSalesModelView> customers) {
        this.customers = customers;
    }
}
