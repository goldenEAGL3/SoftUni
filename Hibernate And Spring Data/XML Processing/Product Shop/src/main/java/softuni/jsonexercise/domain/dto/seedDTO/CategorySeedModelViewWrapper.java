package softuni.jsonexercise.domain.dto.seedDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategorySeedModelViewWrapper implements Serializable {

    @XmlElement(name = "category")
    private List<CategorySeedDTO> category;

    public List<CategorySeedDTO> getCategory() {
        return this.category;
    }

    public void setCategory(List<CategorySeedDTO> category) {
        this.category = category;
    }
}
