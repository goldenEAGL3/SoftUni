package mostwanted.domain.dtos.raceentries;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "race-entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntryImportDto {

    @XmlAttribute(name = "has-finished")
    private String hasFinished;

    @XmlAttribute(name = "finish-time")
    private Double finishTime;

    @XmlAttribute(name = "car-id")
    private int car;

    @XmlElement(name = "racer")
    private String racerName;

    public String getHasFinished() {
        return this.hasFinished;
    }

    public void setHasFinished(String hasFinished) {
        this.hasFinished = hasFinished;
    }

    public Double getFinishTime() {
        return this.finishTime;
    }

    public void setFinishTime(Double finishTime) {
        this.finishTime = finishTime;
    }

    public int getCar() {
        return this.car;
    }

    public void setCar(int car) {
        this.car = car;
    }

    public String getRacerName() {
        return this.racerName;
    }

    public void setRacerName(String racerName) {
        this.racerName = racerName;
    }
}
