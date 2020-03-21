package xmlprocessing.goldeneagle.domain.dto.seedDto;


import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategorySeedDTO {

    @XmlElement
    @Size(min = 3, max = 15, message = "Category name should be between 3 and 15 symbols long!")
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
