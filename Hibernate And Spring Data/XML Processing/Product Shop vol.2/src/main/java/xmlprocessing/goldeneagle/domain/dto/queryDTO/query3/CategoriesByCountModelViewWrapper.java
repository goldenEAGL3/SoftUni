package xmlprocessing.goldeneagle.domain.dto.queryDTO.query3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesByCountModelViewWrapper {

    @XmlElement(name = "category")
    List<CategoryByNameModelView> categories;

    public List<CategoryByNameModelView> getCategories() {
        return this.categories;
    }

    public void setCategories(List<CategoryByNameModelView> categories) {
        this.categories = categories;
    }
}
