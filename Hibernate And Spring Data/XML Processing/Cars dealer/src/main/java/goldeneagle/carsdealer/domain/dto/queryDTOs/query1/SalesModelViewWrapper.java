package goldeneagle.carsdealer.domain.dto.queryDTOs.query1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Set;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesModelViewWrapper {

    @XmlElement(name = "sale")
    private Set<SalesModelView> sales;

    public Set<SalesModelView> getSales() {
        return this.sales;
    }

    public void setSales(Set<SalesModelView> sales) {
        this.sales = sales;
    }
}
