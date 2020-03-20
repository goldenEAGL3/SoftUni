package goldeneagle.carsdealer.domain.dto.seedDTOs;

import com.google.gson.annotations.Expose;

public class CarSeedDTO {

    @Expose
    private String model;
    @Expose
    private String make;
    @Expose
    private Long travelledDistance;

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Long getTravelledDistance() {
        return this.travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }
}
