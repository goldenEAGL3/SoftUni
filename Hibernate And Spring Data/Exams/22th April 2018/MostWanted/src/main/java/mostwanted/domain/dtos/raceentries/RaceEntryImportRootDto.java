package mostwanted.domain.dtos.raceentries;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "race-entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntryImportRootDto {

    @XmlElement(name = "race-entry")
    private List<RaceEntryImportDto> raceEntry;

    public List<RaceEntryImportDto> getRaceEntry() {
        return this.raceEntry;
    }

    public void setRaceEntry(List<RaceEntryImportDto> raceEntry) {
        this.raceEntry = raceEntry;
    }
}