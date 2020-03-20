package goldeneagle.carsdealer.domain.dto.queryDTOs.query1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarModelView {
    @Expose
    @SerializedName("Make")
    private String make;

    @Expose
    @SerializedName("Model")
    private String model;

    @Expose
    @SerializedName("TravelledDistance")
    private double travelledDistance;

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

    public double getTravelledDistance() {
        return this.travelledDistance;
    }

    public void setTravelledDistance(double travelledDistance) {
        this.travelledDistance = travelledDistance;
    }
}
