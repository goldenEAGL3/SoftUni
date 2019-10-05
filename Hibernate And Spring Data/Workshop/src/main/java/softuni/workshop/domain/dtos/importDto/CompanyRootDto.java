package softuni.workshop.domain.dtos.importDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "companies")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyRootDto implements Serializable {

    @XmlElement
    private List<CompanyDto> company;

    public List<CompanyDto> getCompany() {
        return this.company;
    }

    public void setCompany(List<CompanyDto> company) {
        this.company = company;
    }
}
