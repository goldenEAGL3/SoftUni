package goldeneagle.carsdealer.domain.dto.queryDTOs.query1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SalesModelView {

    @Expose
    @SerializedName("Car")
    private CarModelView car;
    @Expose
    @SerializedName("Discount")
    private double discountPercentage;

    public CarModelView getCar() {
        return this.car;
    }

    public void setCar(CarModelView car) {
        this.car = car;
    }

    public double getDiscountPercentage() {
        return this.discountPercentage * 100;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
