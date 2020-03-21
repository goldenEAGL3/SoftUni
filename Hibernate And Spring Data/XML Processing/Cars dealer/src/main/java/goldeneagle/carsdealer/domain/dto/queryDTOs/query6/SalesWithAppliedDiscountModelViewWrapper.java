package goldeneagle.carsdealer.domain.dto.queryDTOs.query6;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesWithAppliedDiscountModelViewWrapper {

    @XmlElement(name = "sale")
    private List<SalesWithAppliedDiscountModelView> sales;

    public List<SalesWithAppliedDiscountModelView> getSales() {
        return this.sales;
    }

    public void setSales(List<SalesWithAppliedDiscountModelView> sales) {
        this.sales = sales;
    }
}
