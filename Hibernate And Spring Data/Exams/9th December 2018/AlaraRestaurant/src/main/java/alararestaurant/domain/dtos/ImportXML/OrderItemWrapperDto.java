package alararestaurant.domain.dtos.ImportXML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderItemWrapperDto {

    @XmlElement(name = "item")
    private List<OrderItemDto> orderItem;

    public List<OrderItemDto> getOrderItem() {
        return this.orderItem;
    }

    public void setOrderItem(List<OrderItemDto> orderItem) {
        this.orderItem = orderItem;
    }
}
