package mostwanted.domain.dtos.races;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntryImportRootDto {

    @XmlElement
    private List<EntryImportDto> entry;

    public List<EntryImportDto> getEntry() {
        return this.entry;
    }

    public void setEntry(List<EntryImportDto> entry) {
        this.entry = entry;
    }
}
