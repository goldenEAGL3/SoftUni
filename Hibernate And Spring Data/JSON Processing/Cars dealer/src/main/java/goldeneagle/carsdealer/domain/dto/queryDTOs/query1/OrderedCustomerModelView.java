package goldeneagle.carsdealer.domain.dto.queryDTOs.query1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.List;

public class OrderedCustomerModelView {

    @Expose()
    @SerializedName("Id")
    private int id;
    @Expose()
    @SerializedName("Name")
    private String name;
    @Expose()
    @SerializedName("BirthDate")
    private LocalDateTime birthDate;
    @Expose()
    @SerializedName("IsYoungDriver")
    private boolean isYoungDriver;
    @Expose()
    @SerializedName("Sales")
    private List<SalesModelView> sales;

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

    public LocalDateTime getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return this.isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public List<SalesModelView> getSales() {
        return this.sales;
    }

    public void setSales(List<SalesModelView> sales) {
        this.sales = sales;
    }
}
