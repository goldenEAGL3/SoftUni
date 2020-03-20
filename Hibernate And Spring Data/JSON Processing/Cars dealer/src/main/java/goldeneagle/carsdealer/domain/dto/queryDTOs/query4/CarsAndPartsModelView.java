package goldeneagle.carsdealer.domain.dto.queryDTOs.query4;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarsAndPartsModelView {

    @Expose
    private CarModelView car;
    @Expose
    List<PartModelView> parts;

    public CarModelView getCar() {
        return this.car;
    }

    public void setCar(CarModelView car) {
        this.car = car;
    }

    public List<PartModelView> getParts() {
        return this.parts;
    }

    public void setParts(List<PartModelView> parts) {
        this.parts = parts;
    }
}
