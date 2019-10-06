package alararestaurant.domain.dtos.ImportXML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "orders")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderWrapperDto {

    private List<OrderDto> order;

    public List<OrderDto> getOrder() {
        return this.order;
    }

    public void setOrder(List<OrderDto> order) {
        this.order = order;
    }
}
