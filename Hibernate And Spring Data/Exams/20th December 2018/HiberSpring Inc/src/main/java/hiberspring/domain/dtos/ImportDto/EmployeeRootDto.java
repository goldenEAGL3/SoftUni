package hiberspring.domain.dtos.ImportDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeRootDto {

    @XmlElement
    private List<EmployeeDto> employee;

    public List<EmployeeDto> getEmployee() {
        return this.employee;
    }

    public void setEmployee(List<EmployeeDto> employee) {
        this.employee = employee;
    }
}
