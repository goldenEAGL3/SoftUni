package softuni.workshop.domain.dtos.importDto;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "company")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyDto implements Serializable {

    @XmlAttribute
    private String name;

    @Size(min = 3, max = 15, message = "Company name must be between 3 and 15 symbols long.")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
