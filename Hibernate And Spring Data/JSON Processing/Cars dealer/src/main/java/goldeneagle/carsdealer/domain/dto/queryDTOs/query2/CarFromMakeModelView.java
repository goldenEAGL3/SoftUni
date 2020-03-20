package goldeneagle.carsdealer.domain.dto.queryDTOs.query2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarFromMakeModelView {

    @Expose
    @SerializedName("Id")
    private int id;
    @Expose
    @SerializedName("Make")
    private String make;
    @Expose
    @SerializedName("Model")
    private String model;
    @Expose
    @SerializedName("TravelledDistance")
    private double travelledDistance;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
