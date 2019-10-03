package softuni.jsonexercise.domain.dto.queryDTO.query3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;
@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryModelViewWrapper implements Serializable {

    @XmlElement
    private List<CategoryModelViewDto> category;

    public CategoryModelViewWrapper(List<CategoryModelViewDto> categories) {
        this.category = categories;
    }

    public CategoryModelViewWrapper() {
    }

    public List<CategoryModelViewDto> getCategory() {
        return this.category;
    }

    public void setCategory(List<CategoryModelViewDto> category) {
        this.category = category;
    }
}
