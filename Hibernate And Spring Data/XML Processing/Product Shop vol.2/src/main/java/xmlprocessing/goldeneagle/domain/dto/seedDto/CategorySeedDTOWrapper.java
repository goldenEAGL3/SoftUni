package xmlprocessing.goldeneagle.domain.dto.seedDto;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategorySeedDTOWrapper {

    @XmlElement(name = "category")
    private List<CategorySeedDTO> categories;

    public List<CategorySeedDTO> getCategories() {
        return this.categories;
    }

    public void setCategories(List<CategorySeedDTO> categories) {
        this.categories = categories;
    }
}
