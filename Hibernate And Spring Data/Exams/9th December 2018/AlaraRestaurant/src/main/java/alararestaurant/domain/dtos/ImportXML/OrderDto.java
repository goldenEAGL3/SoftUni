package alararestaurant.domain.dtos.ImportXML;

import alararestaurant.domain.entities.enums.OrderType;
import alararestaurant.util.XmlParserImpl;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDto {

    @XmlElement
    @NotNull
    private String customer;

    @XmlElement
    private String employee;

    @XmlElement(name = "date-time")
    @XmlJavaTypeAdapter(XmlParserImpl.DateTimeAdapter.class)
    private Date dateTime;

    @XmlElement
    private OrderType type;

    @XmlElement
    private OrderItemWrapperDto items;

    public String getCustomer() {
        return this.customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getEmployee() {
        return this.employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public Date getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public OrderType getType() {
        return this.type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public OrderItemWrapperDto getItems() {
        return this.items;
    }

    public void setItems(OrderItemWrapperDto items) {
        this.items = items;
    }
}
