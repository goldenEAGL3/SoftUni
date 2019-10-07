package hiberspring.domain.dtos.ImportDto;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductDto {

    @XmlAttribute
    @NotNull
    private String name;
    @XmlAttribute
    @NotNull
    private Integer clients;
    @XmlElement
    @NotNull
    private String branch;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getClients() {
        return this.clients;
    }

    public void setClients(Integer clients) {
        this.clients = clients;
    }

    public String getBranch() {
        return this.branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
