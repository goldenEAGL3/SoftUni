package mostwanted.domain.dtos.races;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "races")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceImportRootDto {

    @XmlElement
    private List<RaceImportDto> race;

    public List<RaceImportDto> getRace() {
        return this.race;
    }

    public void setRace(List<RaceImportDto> race) {
        this.race = race;
    }
}
