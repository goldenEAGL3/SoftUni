package goldeneagle.carsdealer.domain.dto.queryDTOs.query3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocalSupplierModelView {

    @Expose
    @SerializedName("Id")
    private int id;
    @Expose
    @SerializedName("Name")
    private String name;
    @Expose
    private int partsCount;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPartsCount() {
        return this.partsCount;
    }

    public void setPartsCount(int partsCount) {
        this.partsCount = partsCount;
    }
}
