package goldeneagle.carsdealer.domain.dto.queryDTOs.query4;


import javax.xml.bind.annotation.*;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsAndPartsModelView {

    @XmlAttribute
    private String make;
    @XmlAttribute
    private String model;
    @XmlAttribute(name = "travelled-distance")
    private double travelledDistance;
    @XmlElement(name = "parts")
    private PartModelViewWrapper parts;

    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getTravelledDistance() {
        return this.travelledDistance;
    }

    public void setTravelledDistance(double travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public PartModelViewWrapper getParts() {
        return this.parts;
    }

    public void setParts(PartModelViewWrapper parts) {
        this.parts = parts;
    }
}
